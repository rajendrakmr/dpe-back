package in.gov.vocport.commonBeans;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CommonUtils {
    public Date getCurrentTime() {
        long currentTimesInMillis = System.currentTimeMillis();
        return new Date(currentTimesInMillis);
    }
}
