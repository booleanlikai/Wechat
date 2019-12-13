import com.likai.gateway.model.menu.button;
import com.likai.gateway.model.menu.menuRequest;
import com.likai.gateway.service.Imp.accessTokenGetImpl;
import com.likai.gateway.service.Imp.menu.CreateMenuInfoImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class GatewayApplicationTests {


    @Test
    public void contextLoads() {
        accessTokenGetImpl accessTokenGetImpl = new accessTokenGetImpl();
//        tokenResponse tokenResponse = accessTokenGetImpl.getaccess();
//
//        if (tokenResponse != null && (tokenResponse.getErrcode() == null || tokenResponse.getErrcode().equals("")))
//        System.out.println("hhhh");


    }

    @Test
    public void testss(){
        menuRequest menuRequest=new menuRequest();
        button button1=new button();
        button1.setType("click");
        button1.setKey("10001");
        button1.setName("測試1");
        button button2=new button();
        button2.setType("click");
        button2.setKey("10002");
        button2.setName("測試2");
        button button3=new button();
        button3.setType("click");
        button3.setKey("10003");
        button3.setName("測試3");
        button button4=new button();
        button4.setType("click");
        button4.setKey("10004");
        button4.setName("測試4");
        button button5=new button();
        button5.setType("click");
        button5.setKey("10005");
        button5.setName("測試5");
        button button6=new button();
        button6.setType("click");
        button6.setKey("10006");
        button6.setName("測試6");
        button button7=new button();
        button7.setType("click");
        button7.setKey("10007");
        button7.setName("測試7");
        List<button> list1=new ArrayList<>();
        list1.add(button1);
        list1.add(button2);
        list1.add(button3);
        list1.add(button7);
        List<button> list2=new ArrayList<>();
        list2.add(button4);
        list2.add(button5);
        list2.add(button6);
        button2.setSub_button(list2);
//        menuRequest.setList(list1);
//        CreateMenuInfoImpl pushMenuInfo=new CreateMenuInfoImpl();
//        pushMenuInfo.pushMenu(menuRequest);
    }

}
