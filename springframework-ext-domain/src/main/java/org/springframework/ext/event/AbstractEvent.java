package org.springframework.ext.event;

import com.google.common.base.Throwables;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ext.common.helper.JsonHelper;
import org.springframework.ext.common.setting.Context;

import java.util.concurrent.TimeUnit;

/**
 * @author only
 *         Date 2015/8/18.
 */
public abstract class AbstractEvent<T> implements Event {
    protected T module;
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final EventFuture future = new EventFuture();

    public AbstractEvent(T module) {
        this.module = module;
    }

    public boolean waitDone(long timeout) {
        try {
            return future.get(timeout, TimeUnit.MILLISECONDS) != null;
        } catch (Exception e) {
            logger.error(String.format("waitDone\01event\02%s\01timeout\02%s\01exception\02%s", JsonHelper.toJson(this), TimeUnit.MILLISECONDS.toMillis(timeout), Throwables.getRootCause(e)));

            Throwables.throwIfUnchecked(e);
            throw new RuntimeException(e);
        }
    }

    public void onSuccess() {
        String message = "event complete successful";

        future.setSuccess(message);

        if (Context.debug() || logger.isInfoEnabled()) {
            logger.warn(String.format("handleEvent\01event\02%s\01message\02%s", this.getClass().getSimpleName(), message));
        }
    }

    public void onFailure(String message) {
        message = StringUtils.isNotBlank(message) ? message : "event complete failure";

        future.setFailure(message);

        logger.warn(String.format("handleEvent\01event\02%s\01message\02%s", this.getClass().getSimpleName(), message));
    }

    public void onException(Exception e) {
        Throwable t = Throwables.getRootCause(e);

        future.setException(t.getMessage());

        logger.error(String.format("handleEvent\01event\02%s\01exception\02%s", this.getClass().getSimpleName(), t.getMessage()), t);
    }

    public T getModule() {
        return module;
    }

    public boolean hasModule() {
        return module != null;
    }

    public EventFuture getFuture() {
        return future;
    }

    public String toJson() {
        return JsonHelper.toJson(this);
    }

    @Override
    public String toString() {
        return toJson();
    }
}
