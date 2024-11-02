package secret;

import com.cbq.bilibili.answer.utils.ChatGLMChat;
import com.cbq.bilibili.answer.utils.ChatGLMMessage;
import com.cbq.bilibili.answer.utils.TongYiUtil;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestXH {

    @Test
    public void test(){
        ChatGLMChat build = new ChatGLMChat().builder()
                .addMessage(new ChatGLMMessage("user", "接下来你只需要回复对应题正确答案的选项ans_hash值无需回复任何其他内容和解释,如果有多个答案正确也只需要回答一个ans_hash即可。"))
                .addMessage(new ChatGLMMessage("user", "{\"code\":0,\"message\":\"0\",\"ttl\":1,\"data\":{\"id\":460673,\"question\":\"一种可爱的青蛙，它有着裸露湿润的皮肤，可以辅助（ ）\",\"answers\":[{\"ans_text\":\"适应环境\",\"ans_hash\":\"5f1acda7d6e47e3c0a62e908bc64c996\"},{\"ans_text\":\"躲避天敌\",\"ans_hash\":\"48c44b42554817775e8a8f22ace731db\"},{\"ans_text\":\"捕虫\",\"ans_hash\":\"aea217c904993fda471c2f623a8453d9\"},{\"ans_text\":\"呼吸\",\"ans_hash\":\"a31311d8ca1cee4a5de1165d20001463\"}],\"question_num\":1,\"source\":278871689,\"like\":44,\"like_state\":0,\"start_time\":1730428345,\"stage\":2}}")).build();

        String chatMessage = TongYiUtil.getChatMessage("sk-bdc75ad31f0942c8b6a639746d12b6a0", build);
        System.out.println(chatMessage);
        try {
            System.out.println(URLEncoder.encode( "狄拉克方程", "UTF-8" ));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
