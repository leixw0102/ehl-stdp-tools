package jdk.net.xdevelop.timeserver;

import jdk.net.xdevelop.nioserver.Request;
import jdk.net.xdevelop.nioserver.Response;
import jdk.net.xdevelop.nioserver.event.EventAdapter;

import java.util.*;
import java.text.DateFormat;

/**
 * ʱ���ѯ������
 */
public class TimeHandler extends EventAdapter {
    public TimeHandler() {
    }

    public void onWrite(Request request, Response response) throws Exception {
        String command = new String(request.getDataInput());
        String time = null;
        Date date = new Date();

        // �жϲ�ѯ����
        if (command.equals("GB")) {
            // ���ĸ�ʽ
            DateFormat cnDate = DateFormat.getDateTimeInstance(DateFormat.FULL,
                DateFormat.FULL, Locale.CHINA);
            time = cnDate.format(date);
        }
        else {
            // Ӣ�ĸ�ʽ
            DateFormat enDate = DateFormat.getDateTimeInstance(DateFormat.FULL,
                DateFormat.FULL, Locale.US);
            time = enDate.format(date);
        }

        response.send(time.getBytes());
    }
}
