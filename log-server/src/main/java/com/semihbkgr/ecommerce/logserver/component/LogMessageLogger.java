package com.semihbkgr.ecommerce.logserver.component;

import com.semihbkgr.ecommerce.modelcommon.message.LogMessage;

public interface LogMessageLogger {
    
    void log(LogMessage logMessage);
    
}
