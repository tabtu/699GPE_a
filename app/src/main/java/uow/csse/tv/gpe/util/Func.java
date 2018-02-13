package uow.csse.tv.gpe.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vian on 2/10/2018.
 */

public class Func {

    public String convertLong2String(Long date) {
        String dateStr = new SimpleDateFormat("MM/dd/yyyy").format(new Date(date));
        return dateStr;
    }

}
