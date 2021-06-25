package com.example.korean_translator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class SecondPage extends AppCompatActivity implements EditSection.editSection{

    Button $BTNtranslate, $BTNtxtRom;
    EditText $ETsearch;
    TextView $TVhangul, $TVromanized, $TVtxtHan;

    DataBaseHelper DBH = new DataBaseHelper(SecondPage.this);

    String _ETsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        $BTNtranslate = findViewById(R.id.BTNtranslate);
        $ETsearch = findViewById(R.id.ETsearch);
        $TVhangul =  findViewById(R.id.TVhangul);
        $TVromanized = findViewById(R.id.TVRomanized);
        $TVtxtHan = findViewById(R.id.TVtxtHan);
        $BTNtxtRom =  findViewById(R.id.BTNtxtRom);

        $TVtxtHan.setVisibility(View.INVISIBLE);
        $BTNtxtRom.setVisibility(View.INVISIBLE);
        $TVhangul.setVisibility(View.INVISIBLE);

        String[] English = new String[]{
                "Father","Mother","Sister","Brother","Sorry","Hi","I miss you","Thank you","Why","What",
                "week","Year","Today","Tomorrow","Yesterday","Calendar","Second","Hour","Minute","O’clock",
                "Clock","I love you","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday","January",
                "February","March","April","May","June","July","August","September","October","November",
                "December","One","Two","Three","Four","Five","Six","Seven","Eight","Nine",
                "Ten","Coffee","Beer","Tea","Wine","Water","Beef","Pork","Chicken","Lamb",
                "Fish","Foot","Leg","Head","Arm","Hand","Finger","Body","Stomach","Back",
                "Chest","Nurse","Employee","Police officer","Cook","Engineer","Doctor","Manager","Teacher","Programmer",
                "Salesman","School","City","Train station","Bathroom","Country","Room","After","Now","Please",
                "No","Yes","Excuse me","Right","Left","Really","Awesome","Beautiful","Good bye","Understood"
        };
        String[] Hangul = new String[]{
                "아빠","엄마","언니","오빠","미안해","안녕하세요","보고싶다","감사합니다","왜","뭐",
                "주","년","오늘","내일","어제","달력","초","시","분","정각",
                "시계","사랑해","월요일","화요일","수요일","목요일","금요일","토요일","일요일","일월",
                "이월","삼월","사월","오월","유월","칠월","팔월","구월","시월","십일월",
                "십이월","하나","둘","셋","넷","다섯","여섯","일곱","여덟","아홉",
                "열","커피","맥주","차","포도주","물","쇠고기","돼지고기","닭고기","양고기",
                "생선","발","다리","머리","팔","손","손가락","몸","배","등",
                "가슴","간호사","직원","경찰관","요리사","엔지니어","의사","매니저","선생님","프로그래머",
                "판매원","학교","도시","기차역","화장실","나라","방","후","지금","주세요",
                "아니요","네","저기요","오른쪽","왼쪽","진짜","대박","예쁘다","안녕","알았어"
        };
        String[] Romanized = new String[]{
                "Appa","Eomma","Eonnie","Oppa","Mianhae","Annyeonghaseyo","Bogoshipda","Kamsahamnida","Waeyo","Mwo",
                "Ju","Nyeon","oneul","naeil","eoje","dallyeok","cho","si","bun","jeonggak",
                "sigye","Saranghae","woryoil","hwayoil","suyoil","mogyoil","geumyoil","toyoil","iryoil","irwol",
                "iwol","samwol","sawol","owol","yuwol","chirwol","parwol","guwol","siwol","sibirwol",
                "sibiwol","Ha-na","Tul","set","net","Ta-seot","Yeoseot","Il-gop","Yeo-deol","a-hop",
                "yeol","Keopi","maekjju","cha","podoju","mul","soegogi","dwaejigogi","dakgogi","yanggogi",
                "saengseon","bal","dari","meori","pal","son","songgarak","Mom","Bae","Deung",
                "gaseum","ganhosa","jigwon","gyeongchalgwan","yorisa","enjinieo","Uisa","maenijeo","seonsaengnim","peurogeuraemeo",
                "panmaewon","haggyo","dosi","gichayeog","hwajangsil","nara","Bang","hu","jigeum","juseyo",
                "aniyo","Ne","jeogiyo","oreunjjok","oenjjok","Jinjja","Daebak","Yeppuda","Annyeong","Arraseo"
        };

        int i = 0;
        while (i<=99){
            DataBaseDatas data = new DataBaseDatas(English[i], Hangul[i], Romanized[i]);
            DBH.add(data, DBH.getWritableDatabase());
            i++;
        }

        $BTNtranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 _ETsearch = $ETsearch.getText().toString();

                if(_ETsearch.isEmpty()){
                    $TVtxtHan.setVisibility(View.INVISIBLE);
                    $TVhangul.setVisibility(View.INVISIBLE);
                    $TVromanized.setVisibility(View.INVISIBLE);
                    $BTNtxtRom.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondPage.this,"Please Enter Text",Toast.LENGTH_SHORT).show();
                }else{
                    Cursor cursor = DBH.displayData(_ETsearch, DBH.getReadableDatabase());

                    if (cursor.moveToFirst()){
                        String han = cursor.getString(cursor.getColumnIndex(DBH.HANGUL_TERM));
                        String rom = cursor.getString(cursor.getColumnIndex(DBH.ROMANIZED_TERM));
                        $TVhangul.setText(han);
                        $TVromanized.setText(rom);

                        $TVtxtHan.setVisibility(View.VISIBLE);
                        $BTNtxtRom.setVisibility(View.VISIBLE);

                        $TVhangul.setVisibility(View.VISIBLE);
                        $TVromanized.setVisibility(View.INVISIBLE);

                        $BTNtxtRom.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                $TVromanized.setVisibility(View.VISIBLE);
                                $BTNtxtRom.setVisibility(View.INVISIBLE);
                            }
                        });

                    }else{
                        MaterialAlertDialogBuilder builder =  new MaterialAlertDialogBuilder(SecondPage.this);

                        builder.setTitle("UNKNOWN: "+_ETsearch);
                        builder.setMessage("Click [ EDIT ] to save.");
                        builder.setPositiveButton("EDIT", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditSection ES = new EditSection();
                                ES.show(getSupportFragmentManager(), "Save_Unknown_Word"); //Custom alert Dialog
                            }
                        });
                        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                /* // */
                            }
                        });builder.show();

                    }
                }
            }
        });

    }

    @Override
    public void setData(String han, String rom) {
        DataBaseDatas saveData = new DataBaseDatas();
        saveData.setEnglishWord(_ETsearch);
        saveData.setHangulWord(han);
        saveData.setRomanizedWord(rom);
        DBH.add(saveData, DBH.getWritableDatabase());
    }
}