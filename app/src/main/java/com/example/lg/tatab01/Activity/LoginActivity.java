package com.example.lg.tatab01.Activity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.lg.tatab01.MAuthVO;
import com.example.lg.tatab01.NetworkTask2;
import com.example.lg.tatab01.ProjectList.ProjectAsync;
import com.example.lg.tatab01.R;
import com.example.lg.tatab01.SharedpreferencesCookie;
import com.example.lg.tatab01.URLVO;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.*;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

/**
 * Created by LG on 2018-11-16.
 */

public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    // cookie 저장할 sharedPreferences 선언
    SharedpreferencesCookie sharedpreferencesCookie = SharedpreferencesCookie.getInstance();


    private FirebaseAuth mAuth;
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient mGoogleApiClient;

    // 공유할 contxt 변수
    public static Context context = null;

    @Override
    public void onStart() {
        super.onStart();
        // 활동을 초기화할 때 사용자가 현재 로그인되어 있는지 확인합니다.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.lg.tatab01.R.layout.activity_login);

        context = getApplicationContext();

        // GoogleSignInOptions 생성
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder
                (GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        // 로그인 작업의 onCreate 메소드에서 FirebaseAuth 개체의 공유 인스턴스를 가져옵니다.
        mAuth = FirebaseAuth.getInstance();

        // 로그인 버튼 이벤트 > signInIntent 호출
        SignInButton login_btn_google = (SignInButton) findViewById(R.id.Google_Login);

        login_btn_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("알림", "구글 LOGIN");
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }

        });


        // 로그아웃 버튼 클릭 이벤트 > dialog 예/아니오
//        Button logout_btn_google = (Button) findViewById(R.id.logout_google);
//        logout_btn_google.setOnClickListener(new View.OnClickListener() {

//            @Override
//            public void onClick(final View view) {
//                Log.v("알림", "구글 LOGOUT");
//                AlertDialog.Builder alt_bld = new AlertDialog.Builder(view.getContext());
//                alt_bld.setMessage("로그아웃 하시겠습니까?").setCancelable(false)
//                        .setPositiveButton("네",

//                                new DialogInterface.OnClickListener() {
//                                    public void onClick(DialogInterface dialog, int id) {
//                                        // 네 클릭
//                                        // 로그아웃 함수 call
//                                        signOut();
//                                    }
//                                }).setNegativeButton("아니오",
//
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                // 아니오 클릭. dialog 닫기.
//                                dialog.cancel();
//                            }
//                        });
//
//                AlertDialog alert = alt_bld.create();
//
//                // 대화창 클릭시 뒷 배경 어두워지는 것 막기
//                //alert.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                // 대화창 제목 설정
//
//                alert.setTitle("로그아웃");
//
//                // 대화창 아이콘 설정
//
//                alert.setIcon(R.drawable.check_dialog_64);
//
//                // 대화창 배경 색 설정
//
//                alert.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(255, 62, 79, 92)));
//
//                alert.show();
//            }
//        });

    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                Log.v("알림", "google sign 성공, FireBase Auth.");
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);

                Log.d("알림", "account 확인 : " + account.getEmail());
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//                Log.d("syj", "[LoginActivity] UID : " + user.getEmail());
                Log.d("syj", "[LoginActivity] UID : " + account.getEmail());


                //
                sharedpreferencesCookie.setContext(getApplicationContext());

                // TODO
                // AsyncTask를 통해 HttpURLConnection 수행.
                // url 설정 ( URLVO( mapping 할 url 주소 ) )
                URLVO urlvo = new URLVO("mProjectList.do");

                // 전달할 파라미터 설정
                ContentValues contentValues = new ContentValues();
//                contentValues.put("login_email", user.getEmail());
                contentValues.put("login_email", account.getEmail());

                ProjectAsync projectAsync = new ProjectAsync(urlvo.getUrl(), contentValues);
//                projectAsync.setActivityData(getApplicationContext(), user.getEmail());
                projectAsync.setActivityData(getApplicationContext(), account.getEmail());

                projectAsync.execute();
                //

//                Intent intent = new Intent(getApplicationContext(), ProjectListActivity.class);
//                intent.putExtra("mEmail", user.getEmail());
//                startActivity(intent);

            } else {
                // Google Sign In failed, update UI appropriately
                Log.v("알림", result.isSuccess() + " Google Sign In failed. Because : " + result.getStatus().toString());
            }

        }

    }


    // 사용자가 정상적으로 로그인한 후에 GoogleSignInAccount 개체에서 ID 토큰을 가져와서

    //Firebase 사용자 인증 정보로 교환하고 Firebase 사용자 인증 정보를 사용해 Firebase에 인증합니다.

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.v("알림", "ONCOMPLETE");
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.v("알림", "!task.isSuccessful()");
                            Toast.makeText(LoginActivity.this, "인증에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        } else {
                            Log.v("알림", "task.isSuccessful()");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // Toast.makeText(LoginActivity.this, "FireBase 아이디 생성이 완료 되었습니다", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override

    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.v("알림", "onConnectionFailed");
    }


    // 로그아웃
    public void signOut() {

        mGoogleApiClient.connect();
        mGoogleApiClient.registerConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {

            @Override
            public void onConnected(@Nullable Bundle bundle) {
                mAuth.signOut();
//                if (mGoogleApiClient.isConnected()) {
//                    Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
//
//                        @Override
//                        public void onResult(@NonNull Status status) {
//                            if (status.isSuccess()) {
//                                Log.v("알림", "로그아웃 성공");
//                                setResult(1);
//                            } else {
//                                setResult(0);
//                            }
//                            finish();
//                        }
//                    });
//                }
            }

            @Override
            public void onConnectionSuspended(int i) {

                Log.v("알림", "Google API Client Connection Suspended");
                setResult(-1);
                finish();
            }
        });
    }
}
