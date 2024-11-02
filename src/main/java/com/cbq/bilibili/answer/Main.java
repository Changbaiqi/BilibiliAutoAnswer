package com.cbq.bilibili.answer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cbq.bilibili.answer.utils.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Main {
    static final String access_key = "对应APP端的access_key参数";
    public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
        for (int i = 0; i < 99; ++i) {

            String problem = BilibiliUtils.getProblem(access_key); //获取问题
            JSONObject jsonObject = JSONObject.parseObject(problem);
            String problemId = jsonObject.getJSONObject("data").getString("id");
            ChatGLMChat build = new ChatGLMChat().builder()
                    .addMessage(new ChatGLMMessage("user", "接下来你只需要回复对应题正确答案的选项ans_hash值无需回复任何其他内容和解释,如果有多个答案正确也只需要回答一个ans_hash即可。"))
                    .addMessage(new ChatGLMMessage("user", problem)).build();
            //这边建议是使用通义千问，通义千问的正确率要比其他几个分数高得多的多
            String chatMessage = TongYiUtils.getChatMessage("AI的APIKey", build);
            System.out.println("题目原始数据："+jsonObject.toJSONString());
            System.out.println("AI答案文字："+getAnsText(jsonObject,chatMessage.substring(0,32)));
            System.out.println("AI答案Hash：" + chatMessage.substring(0,32));
            String s = BilibiliUtils.submitAnswer(access_key,problemId, chatMessage.substring(0,32), URLEncoder.encode( getAnsText(jsonObject,chatMessage.substring(0,32)), "UTF-8" ));
            System.out.println("回答后服务器返回json数据：" + s);
        }
    }
    public static String getAnsText(JSONObject jsonObject,String ans_hash){
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("answers");
        for (Object o : jsonArray) {
            JSONObject jsonObject1 = (JSONObject) o;
            if(jsonObject1.getString("ans_hash").equals(ans_hash)){
                return jsonObject1.getString("ans_text");
            }
        }
        return "";
    }

}