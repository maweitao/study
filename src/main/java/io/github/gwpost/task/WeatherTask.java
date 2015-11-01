package io.github.gwpost.task;

import io.github.gwpost.model.OneDayWeatherInfo;
import io.github.gwpost.model.WeatherInfo;
import io.github.gwpost.util.WeatherHelper;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by guwen on 15-10-31.
 */
public class WeatherTask {

    private JavaMailSender mailSender;
    private VelocityEngine velocityEngine;

    final Logger logger = LoggerFactory.getLogger("weatherTask");

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public VelocityEngine getVelocityEngine() {
        return velocityEngine;
    }

    public void setVelocityEngine(VelocityEngine velocityEngine) {
        this.velocityEngine = velocityEngine;
    }

    @Scheduled(cron = "0 30 23 * * ?")
    public void reportWeather() throws MessagingException, MalformedURLException, UnsupportedEncodingException {
        Map<String,String> persons=new LinkedHashMap<String, String>();
        persons.put("gwpost@qq.com","武汉");
        persons.put("807725980@qq.com","西安");
        for (Map.Entry<String, String> person : persons.entrySet()) {
            WeatherInfo weatherInfo= WeatherHelper.resolveWeatherInfo(WeatherHelper.getWeatherInform(person.getValue()));
            if(weatherInfo.getWeatherInfs()[0].getWeather().indexOf("雨")>-1){
                MimeMessage message=mailSender.createMimeMessage();
                MimeMessageHelper helper=new MimeMessageHelper(message,true,"GBK");
                helper.setFrom("gwpost@qq.com");
                helper.setTo(person.getKey());
                OneDayWeatherInfo oneDayWeatherInfo = weatherInfo.getWeatherInfs()[1];
                helper.setSubject("【温馨提示】明天("+oneDayWeatherInfo.getDate()+")可能有雨，出门不要忘记带伞");
                Map<String,Object> model=new HashMap<String,Object>();
                model.put("descLocation","地　　点：");
                model.put("location", oneDayWeatherInfo.getLocation());
                model.put("descWeather","明天天气：");
                model.put("weather", oneDayWeatherInfo.getWeather());
                model.put("descWind","风　　力:");
                model.put("wind", oneDayWeatherInfo.getWind());
                model.put("descTemperture","气　　温：");
                model.put("descWeatherDay","白天天气");
                model.put("descWeatherNight","晚上天气");
                model.put("descDate","天气时间：");
                model.put("date",oneDayWeatherInfo.getDate());

                model.put("temperture", oneDayWeatherInfo.getTempertureOfDay());
                String emailText= VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "mailTemplate/mail.vm",model);
                helper.setText(emailText,true);
                UrlResource imageDay=new UrlResource(oneDayWeatherInfo.getDayPictureUrl());
                UrlResource imageNight=new UrlResource(oneDayWeatherInfo.getNightPictureUrl());
                helper.addInline("imageDay",imageDay);
                helper.addInline("imageNight",imageNight);
                try{
                    mailSender.send(message);
                    logger.info("发送邮件给[" + person.getKey()+"],地区是["+person.getValue()+"],成功");
                }
                catch(MailException e){
                    e.printStackTrace();
                    logger.info("发送邮件给[" + person.getKey()+"],地区是["+person.getValue()+"],失败");
                }
            }
            else{
                logger.info("明天没有雨,不需要发送邮件给[" + person.getKey()+"],地区是["+person.getValue()+"]");
            }

        }
    }
}
