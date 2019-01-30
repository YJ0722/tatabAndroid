package com.example.lg.tatab01;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.net.HttpURLConnection;
import java.util.List;

/**
 * Created by a on 2018-11-16.
 */

public class SharedpreferencesCookie extends Activity {

    // SharedpreferencesCookie 객체를 하나로만 공유하여 사용하기 위해 singleton 패턴 적용
    public static SharedpreferencesCookie instance = new SharedpreferencesCookie();
    public static SharedpreferencesCookie getInstance() {
        return instance;
    }

    String mEmail;

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    // HttpURLConnection 참조 변수.
    HttpURLConnection urlConn = null;

    // 사용할 context객체
    Context context;

    // 쿠키 목록
    List<String> cookies;

    private  SharedpreferencesCookie() {
        super();
    }

    public void setUrlConn(HttpURLConnection urlConn) {
        this.urlConn = urlConn;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    // Set-Cookie에 배열로 돼있는 쿠키들을 스트링 한줄로 변환
    // sharedPreference에 session 쿠키 저장
    public void getCookieHeader() {
        cookies = urlConn.getHeaderFields().get("Set-Cookie");

        // cookies -> [JSESSIONID=15D9AD252BFEE144390F5BC210A24C97; Path=/smartudy; HttpOnly] -> []가 쿠키1개임.
        // Path -> 쿠키가 유효한 경로 ,/smartudy의 하위 경로에 위의 쿠키를 사용 가능.
        if (cookies != null) {
            for (String cookie : cookies) {
                String sessionid = cookie.split(";\\s*")[0];
                //JSESSIONID=15D9AD252BFEE144390F5BC210A24C97 얻음.
                //세션아이디가 포함된 쿠키를 얻었음.
                Log.d("syj", "[SharedPreferencesCookie]" + "sessionId : " + sessionid);

                // sessionCookie 라는 이름의 sharedPreference 호출
                SharedPreferences pref = context.getSharedPreferences("sessionCookie", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = pref.edit();

                //처음 로그인하여 세션아이디를 받은 경우
                if(pref.getString("sessionid",null) == null){
                    Log.d("syj", "[SharedPreferencesCookie]" + "처음 로그인하여 세션 아이디를 pref에 넣었습니다."+sessionid);
                }
                //서버의 세션 아이디 만료 후 갱신된 아이디가 수신된경우
                else if(!pref.getString("sessionid",null).equals(sessionid)){
                    Log.d("syj", "[SharedPreferencesCookie]" + "기존의 세션 아이디"+pref.getString("sessionid",null)+"가 만료 되어서 " + "서버의 세션 아이디 "+sessionid+" 로 교체 되었습니다.");
                }

                edit.putString("sessionid",sessionid);
                edit.apply(); //비동기 처리
            }
        }
    }

    // session 쿠키 값으로 파라미터와 함께 세션 쿠키 전달 셋팅
    public String setCookieHeader(){

        SharedPreferences pref = context.getSharedPreferences("sessionCookie",Context.MODE_PRIVATE);
        // "sessionCookie" 이름으로 sharedPreferences에 저장된 세션 값 가져오기
        String sessionid = pref.getString("sessionid",null);
        if(sessionid!=null) {
            Log.d("syj", "[SharedPreferencesCookie]" + "세션 아이디"+sessionid+"가 요청 헤더에 포함 되었습니다.");
        }

        return sessionid;
    }

}