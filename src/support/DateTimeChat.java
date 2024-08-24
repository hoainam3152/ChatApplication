package support;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeChat {
	public static String getTimeCurrent() {
		// Lấy thời gian hiện tại
	    LocalTime now = LocalTime.now();

	    // Định dạng thời gian theo định dạng "HH:mm:ss" không có phẩy sau giây
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
	    String formattedTime = now.format(formatter);
	    return formattedTime;
//	    // Chuyển đổi thời gian được định dạng sang LocalTime
//	    LocalTime formattedLocalTime = LocalTime.parse(formattedTime, formatter);
//
//	    return formattedLocalTime;
	}
}
