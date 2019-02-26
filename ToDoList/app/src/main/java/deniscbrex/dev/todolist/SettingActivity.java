package deniscbrex.dev.todolist;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingActivity extends AppCompatActivity {

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;

    private boolean mSound; //activat y desactiva el sonido de la app

    public static final int FAST = 0; //fast animations
    public static final int SLOW = 1; //slow animations
    public static final int NONE = 2; //none animation

    private int mAnimationOptions; //cambia el tipo de animacion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        mPrefs = getSharedPreferences("To do list", MODE_PRIVATE);
        mEditor = mPrefs.edit();


        mSound = mPrefs.getBoolean("sound", true);

        CheckBox soundCheckbox = (CheckBox) findViewById(R.id.checkbox_sound);

        if(mSound){
            soundCheckbox.setChecked(true);
        }else{
            soundCheckbox.setChecked(false);
        }

        soundCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSound = !mSound;

                mEditor.putBoolean("sound", mSound);

            }
        });



        mAnimationOptions = mPrefs.getInt("anim option", FAST);

        final RadioGroup radioGroupAnimation = (RadioGroup) findViewById(R.id.rb_animations);
        radioGroupAnimation.clearCheck();

        switch (mAnimationOptions){
            case FAST:
                radioGroupAnimation.check(R.id.rb_animation_fast);
                break;

            case SLOW:
                radioGroupAnimation.check(R.id.rb_animation_slow);
                break;

            case NONE:
                radioGroupAnimation.check(R.id.rb_animation_none);
                break;
        }

        radioGroupAnimation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //Recupera el radiobutton del radio group que esta marcado
                RadioButton rb = (RadioButton) radioGroupAnimation.findViewById(checkedId);

                if(null != rb && checkedId > -1){

                    switch(rb.getId()){
                        case R.id.rb_animation_fast:
                            mAnimationOptions = FAST;
                            break;
                        case R.id.rb_animation_slow:
                            mAnimationOptions = SLOW;
                            break;

                        case R.id.rb_animation_none:
                            mAnimationOptions = NONE;
                            break;
                    }


                    mEditor.putInt("anim option", mAnimationOptions);


                }
            }
        });



    }

    @Override
    protected void onPause() {
        super.onPause();

        mEditor.commit();
    }
}
