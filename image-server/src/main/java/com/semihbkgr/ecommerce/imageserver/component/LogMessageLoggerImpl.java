package com.semihbkgr.ecommerce.imageserver.component;

import com.semihbkgr.ecommerce.modelcommon.message.LogMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j(topic = "file-logger")
public class LogMessageLoggerImpl implements LogMessageLogger {

    @Override
    public void log(LogMessage logMessage) {
        log.info("{}",logMessage);
    }

}
