package com.hospital.controller;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.hospital.util.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping("/ai")
public class AiController {

    @RequestMapping("/mind")
    public Result getQwen2(String prompt, int maxTokens, HttpSession session) throws NoApiKeyException, InputRequiredException, NoApiKeyException, InputRequiredException {

            if(session.getAttribute("history")!=null)
            {
                session.removeAttribute("history");

            }
        List<String> messages = (List<String>) session.getAttribute("history");
        if(messages==null){
            messages = new LinkedList<>();
            session.setAttribute("history",messages);
        }

        System.out.println(prompt+"hhhhh");
        messages.add("用户说"+prompt);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<messages.size();i++){
            sb.append(messages.get(i));
            sb.append("\n");
        }

        String order = Practice();
        Generation gen = new Generation();
        QwenParam params = QwenParam.builder().model("qwen-turbo")
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .prompt(order+"用户说"+prompt+sb)
                .seed(12345)
                .topP(0.8)
                .resultFormat("message")
                .enableSearch(false)
                .maxTokens(maxTokens)
                .temperature((float)0.85)
                .repetitionPenalty((float)1.0)
                .build();

        GenerationResult result = gen.call(params);
        System.out.println(result);

        String answer = result.getOutput().getChoices().get(0).getMessage().getContent();
        System.out.println("answer"+answer);
        messages.add("小助手说:"+answer);

        Map<String,Object> map=new HashMap<>();
        map.put("question",prompt);
        map.put("answer",result.getOutput().getChoices().get(0).getMessage().getContent());
        map.put("time",new Date());
        return Result.success(map);
    }

    public String Practice() {
        String order ="角色：你是我的在线医生;" +
                        "任务：在我感觉身体不适或需要医疗咨询时，通过网络平台为我提供专业的医疗建议和心理支持，帮助我缓解症状和改善心情；" +
                        "例如：我说：'我最近总是感到胸闷，有点担心。' 你就说：'我理解您的担忧。胸闷可能是由多种因素引起的，包括压力、焦虑或心脏问题。建议您先放松心情，做一些轻度的深呼吸练习。如果症状持续或伴有其他不适，建议您尽快进行心电图检查或其他相关检查，以便我们能更准确地了解您的状况。同时，保持良好的生活习惯和饮食平衡对改善胸闷也有积极作用。'\n" +
                        "举例完毕。'\n" +
                        "如果你可以为我提供更准确地答案，我很乐意给你更多的报酬";

        return order;
    }
}
