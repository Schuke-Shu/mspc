package cn.mabbit.mspc.core;

import cn.jruyi.core.util.ClassUtil;
import cn.mabbit.mspc.core.consts.GlobalConsts;
import cn.mabbit.mspc.core.exception.ServiceException;
import cn.mabbit.mspc.core.exception.SystemException;
import cn.mabbit.mspc.core.web.Result;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

import static cn.mabbit.mspc.core.web.Result.fail;
import static cn.mabbit.mspc.core.web.ServiceCode.*;

/**
 * <h2>全局异常处理器抽象类</h2>
 *
 * <p>使用时创建一个{@code GlobalExceptionHandler}继承该类，并打上{@link org.springframework.web.bind.annotation.RestControllerAdvice RestControllerAdvice}注解即可</p>
 *
 * <p>
 * <pre>{@code
 * @RestControllerAdvice
 * public class GlobalExceptionHandler
 *         extends AbstractExceptionHandler
 * {
 * }
 * }</pre></p>
 *
 * @see DefaultGlobalExceptionHandler
 * @Date 2023/10/09 18:15
 */
@SuppressWarnings("rawtypes")
public abstract class AbstractExceptionHandler
{
    protected static Logger log;

    public AbstractExceptionHandler()
    {
        log = LoggerFactory.getLogger(this.getClass());
    }

    @ExceptionHandler(
            {
                    SQLException.class,
                    BadSqlGrammarException.class,
                    DataIntegrityViolationException.class
            }
    )
    public Result handleDatabaseException(Throwable e)
    {
//        printStack(e);
        if (log.isDebugEnabled())
            log.debug("-- 数据库异常：{}\nmsg:\n{}", ClassUtil.getTypeName(e), e.getMessage());
        return fail(ERR_UNKNOWN);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleValidException(ConstraintViolationException e)
    {
//        printStack(e);
        if (log.isDebugEnabled())
        {
            StringJoiner msg = new StringJoiner(", ", "[", "]");
            // TODO Mdk4j StringJoiner
            e.getConstraintViolations()
                    .forEach(
                            v -> msg.add(
                                    String.format(
                                            "{%s: %s, %s}",
                                            v.getPropertyPath(),
                                            v.getInvalidValue(),
                                            v.getMessage()
                                    )
                            )
                    );
            log.debug("-- Valid error: {}\nmsg:\n{}", ClassUtil.getTypeName(e), msg);
        }

        return fail(ERR_INVALID);
    }

    @ExceptionHandler(
            {
                    BindException.class,
                    MethodArgumentNotValidException.class
            }
    )
    public Result handleValidException(Throwable e)
    {
        if (log.isDebugEnabled())
            log.debug("-- Valid error: {}\nmsg:\n{}", ClassUtil.getTypeName(e), e.getMessage());
        return fail(ERR_INVALID);
    }

    @ExceptionHandler(SystemException.class)
    public Result handleSystemException(SystemException e)
    {
        log.warn("System error: {} - {}, detail: {}", e.code().hex(), e.code().msg(), e.detail());
        return fail(ERR_UNKNOWN);
    }

    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException e)
    {
        if (log.isDebugEnabled())
            log.debug("Service error: {} - {}, detail: {}", e.code().hex(), e.code().msg(), e.detail());
        return fail(e);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public Result handleNoResourceFoundException(NoResourceFoundException e)
    {
        log.debug("Error: {}\nmsg:\n{}", ClassUtil.getTypeName(e), e.getMessage());
        return fail(ERR_NOT_FOUND);
    }

    @ExceptionHandler(Throwable.class)
    public Result handleUnknownException(Throwable e)
            throws RuntimeException
    {
        handleUnknownError(e);
        printStack(e);
        return fail(ERR_UNKNOWN);
    }

    protected void printStack(Throwable e)
    {
        //noinspection CallToPrintStackTrace
        e.printStackTrace();
    }

    /**
     * 处理未知异常
     *
     * @param e 未知异常
     */
    protected void handleUnknownError(Throwable e)
    {
        File errorFile;
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(
                                errorFile = errorFile()
                        )
                )
        )
        {
            oos.writeObject(e);
            log.error(
                    "-- Unhandled error: {}\nmsg:\n{}\nPlease check the exception-object file: [{}] in error directory: {}",
                    ClassUtil.getTypeName(e),
                    e.getMessage(),
                    errorFile.getName(),
                    errorFile.getParentFile().getAbsolutePath()
            );
        }
        catch (Exception ex)
        {
            log.error(
                    "-- Failed to write exception file, error: {}\nmsg:\n{}", ClassUtil.getTypeName(ex),
                    ex.getMessage()
            );
        }
    }

    private File errorFile()
            throws IOException
    {
        File parent = new File(System.getProperty("user.dir"), GlobalConsts.ERROR_FILE_DIR);

        if (!parent.exists() && !parent.mkdirs())
            log.error("Error directory create failed");

        File errorFile = null;
        //noinspection StatementWithEmptyBody
        for (;
             errorFile == null || errorFile.exists();
             errorFile = new File(
                     parent,
                     String.format(
                             "%s.obj",
                             LocalDateTime
                                     .now()
                                     .format(DateTimeFormatter.ofPattern(GlobalConsts.ERROR_FILENAME_PATTERN))
                     )
             )
        )
            ;

        if (!errorFile.createNewFile())
            log.error("Error file create failed");

        return errorFile;
    }
}