import java.util.ArrayList;
import java.util.List;

/**
 * Created by 雷晓武 on 2017/6/16.
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
