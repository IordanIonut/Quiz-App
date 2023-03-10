package com.example.androidproject;

import Classes.*;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private ArrayList<Course> courses = new ArrayList<>();
    TextView name_user;
    SharedPreferences pref;
    DatabaseHelper myDb;
    Course myCourse;
    Question myQuestion;
    Theory myTheory;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        String type = pref.getString("type",null);
        //Log.d("test1",type+"");
        RecyclerView recyclerView = findViewById(R.id.list_course);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AdapterCourse(courses,this,type));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        name_user = (TextView) findViewById(R.id.name_user);
        name_user.setText("Hello, " +pref.getString("name",null));
        myDb = new DatabaseHelper(this);
        Cursor myCursor = myDb.getAllDataCourse();
        if(myCursor.getCount() == 0) {
            insertCourses();
            insertQuestion();
            insertTheory();
        }
        showCourses();
    }
    public void logOut(View view) {
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "The session is stopped!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(HomeActivity.this,MainActivity.class);
        startActivity(i);
    }
    public void showCourses(){
        Cursor myCursor = myDb.getAllDataCourse();
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            courses.add(new Course(myCursor.getInt(0),myCursor.getString(1),false));
        }
    }
    public void insertQuestion(){
        myQuestion = new Question(1,1,"Selecta??i op??iunea(ele) corect??(e) despre obiectul clasei.",4,
                "Clasa Object este o superclas?? a tuturor celorlalte clase.","O variabil?? de tip Object poate de??ine referin???? la orice obiect sau o referin???? nul??",
                "Trebuie s?? extinde??i ??n mod explicit clasa Object.","Toate optiunile.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Dori??i doar s?? declara??i comportamentul unui anumit tip f??r?? nicio implementare, ar trebui s?? ??l declara??i ca interfa????. package com.vq.inheritance;\n" +
                "\n" +
                "public interface VQCourse {\n" +
                "\n" +
                "    public void printCourseDetail();\n" +
                "\n" +
                "    public void getCoursePrice();\n" +
                "\n" +
                "}",2,
                "VQJavaCourse extends VQCourse","VQJavaCourse implements VQCourse",
                "Sintax?? gre??it??.","VQJavaCourse");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Java a furnizat mecanism prin care clasele pot mo??teni starea ??i comportamentul utilizat ??n mod obi??nuit al altor clase. public class VQJavaCourse extends VQCourse {\n" +
                "//Some implementation \n" +
                "}",1,
                "Class VQJavaCourse inherits class VQCourse","VQCourse is a interface.",
                "Sintax?? gre??it??.","VQCourse inherit ");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Ce este un obiect?",4,
                "Fiecare obiect are un tip.","Un obiect poate fi construit folosind alte obiecte.",
                "Un program java const?? din obiecte care interac??ioneaz?? ??ntre ele prin trimiterea de mesaje..","Toate optiunile.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Java este un limbaj de programare orientat pe obiecte ??n care declara??i clasa care este tipul de obiect pe care tocmai l-a??i identificat. Obiectele au st??ri ??i comportamente care sunt metode ??n clas??.",4,
                "Clasa Object este o superclas?? a tuturor celorlalte clase.","O variabil?? de tip Object poate de??ine referin???? la orice obiect sau o referin???? nul??",
                "Trebuie s?? extinde??i ??n mod explicit clasa Object.","Toate optiunile.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Urmeaz?? sintaxa pentru a crea un obiect din clasa Object. Ce declara??ii sunt adev??rate atunci c??nd urm??toarea instruc??iune este executat?? ??n timpul execu??iei.\n" +
                "\n" +
                "\n" +
                "Object obj = nou Object();",3,
                "S-a creat o nou?? instan???? a clasei Object.","Memorie alocat?? pentru obiectul nou, dac?? este disponibil??.",
                "Toate optiunile.", "Obj de referin???? de tip Object este atribuit unei noi instan??e a obiectului creat.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1, "Urm??torul program are o eroare de compilare, pute??i ghici cauza principal?? a acesteia?abstract class VQCourse {\n" +
                "    public String courseName;\n" +
                "    public String courseID;\n" +
                "} public class VirtuQCourseProvide extends VQCourse {\n" +
                "\n" +
                "    public static void main(String[]args) {\n" +
                "        VQCourse course1 = new VQCourse();\n" +
                "         course1.courseID = \"0001\";\n" +
                "         course1.courseName = \"JAVA\";\n" +
                "         System.out.println(course1.toString());\n" +
                "}}",3,
                "Protejat ??nseamn?? c?? numai clasa ??i clasa mo??tenite au acces la membrii proteja??i.","Declara??i clasa VQCourse ca final??.",
                "Nu pute??i instan??ia o clas?? abstract??.", "Nici unul");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1, "Care ar fi rezultatul corect al programului urm??tor?class VQCourse {\n" +
                "    public String courseName;\n" +
                "    public int courseID;\n" +
                "} public class VirtuQCourseProvide {\n" +
                "\n" +
                "    public static void main(String[]args) {\n" +
                "        VQCourse course1 = new VQCourse();\n" +
                "         course1.courseID = 0001;\n" +
                "         course1.courseName = \"0001\";\n" +
                "\n" +
                "        VQCourse course2 = new VQCourse();\n" +
                "         course2 = course1;\n" +
                "\n" +
                "         System.out.println(\"course1.courseID = \" + course1.courseID +\n" +
                "                            \", course2.courseID = \" + course2.courseID);\n" +
                "}}",3,
                "course1.courseID = 0001 course2.courseID = 0001.","course1.courseID = course2.courseID = 1.",
                "course1.courseID = 1, course2.courseID = 1.", "Toate optiunile.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1, "Codul de mai jos are clasa VQClassB care mo??tene??te o alt?? clas?? VQClassA, studia??i cu aten??ie codul ??i alege??i op??iunea corect?? despre ie??irea programului.class VQClassA {\n" +
                "    public VQClassA(int x) {\n" +
                "        this.x = x;\n" +
                "    } protected int x;\n" +
                "}\n" +
                "\n" +
                "public class VQClassB extends VQClassA {\n" +
                "    public VQClassB(int x, int x2, int y) {\n" +
                "        super(x);\n" +
                "        x = x2;\n" +
                "        this.y = y;\n" +
                "    } private VQClassB(int x, int y) {\n" +
                "        super(x);\n" +
                "        this.x = x;\n" +
                "        this.y = y;\n" +
                "    }\n" +
                "\n" +
                "    private int x;\n" +
                "\n" +
                "    private int y;\n" +
                "\n" +
                "    public static void main(String[]args) {\n" +
                "        VQClassA vqa = new VQClassA(10);\n" +
                "\n" +
                "        VQClassB vqb = new VQClassB(20, 10);\n" +
                "\n" +
                "        vqa = vqb;\n" +
                "        System.out.println(vqa.x + \" \" + vqb.y);\n" +
                "    }\n" +
                "}",4,
                "10 10.","12 20.",
                "20 20.", "20 10.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1, "Codul de mai jos are clasa VQClassB care mo??tene??te o alt?? clas?? VQClassA, studia??i cu aten??ie codul ??i alege??i op??iunea corect?? despre ie??irea programului.public class VQClassB {\n" +
                "    class VQClassA {\n" +
                "        public VQClassA(int x) {\n" +
                "            this.x = x;\n" +
                "        } protected int x;\n" +
                "    } public VQClassB(int x, int x2, int y) {\n" +
                "        x = x2;\n" +
                "        this.y = y;\n" +
                "    }\n" +
                "\n" +
                "    private VQClassB(int x, int y) {\n" +
                "        this.x = x;\n" +
                "        this.y = y;\n" +
                "    }\n" +
                "\n" +
                "    private int x;\n" +
                "\n" +
                "    private int y;\n" +
                "\n" +
                "    public static void main(String[]args) {\n" +
                "        VQClassB vqb = new VQClassB(20, 10);\n" +
                "\n" +
                "        VQClassB.VQClassA vqa = new VQClassB(10, 10).new VQClassA(10);\n" +
                "\n" +
                "        System.out.println(vqa.x + \" \" + vqb.x);\n" +
                "    }\n" +
                "}",2,
                "20 20.","10 20.",
                "20 10.", "10 10.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care este forma complet?? de SQL?",1,
                " Structured Query Language."," Structured Question Language.",
                " Structural Query Language.", " Structuring Query Language.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "C??te chei primare pot fi create pe un tabel?",3,
                "o infiniatet.","2.",
                "1.", "25.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre urm??toarele nu este o clauz?? ??n SQL?",1,
                "Below.","limit.",
                "where.", "group by.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre urm??toarele interog??ri ar limita rezultatul la 5 r??nduri?",2,
                " SELECT * FROM DataFlair 5."," SELECT * FROM DataFlair LIMIT 5.",
                " SELECT * FROM DataFlair 5 LIMIT.", " SELECT LIMIT(5) * FROM DataFlair.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, " Care dintre urm??toarele interog??ri descrie instruc??iunea Select corect???",2,
                " Select # From DataFlair."," Select * From DataFlair.",
                " Select From DataFlair.", " Select col1,col2 From.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care sunt comenzile de definire a datelor ??n SQL cunoscute ca popular?\n",4,
                "DML.","UML.",
                "RDEMS.", "DDL.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "??n SQL defini??ia pachetului este alc??tuit?? din care dou?? elemente?\n",2,
                "Defini??iile pachetelor ??i variabilele pachetului."," Corpul pachetului ??i specifica??iile pachetului.",
                " Biblioteci de pachete ??i coduri de pachete.", " Nici una dintre cele de mai sus.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre urm??toarele interog??ri, dac?? este rulat??, va trunchia tabelul DataFlair?\n",2,
                " TRUNCATE DataFlair."," TRUNCATE TABLE DataFlair.",
                " TRUNCATE TABLE.", " Nici unul dintre acestea.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Scrie??i o interogare ??n SQL folosind doar dou?? cuvinte pentru a extrage ora sistemului de acum?\n",3,
                " SELECT RIGHTNOW()."," SELECT CURR().",
                " SELECT NOW().", " SELECT TIME().");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre interog??rile de mai jos pot fi folosite pentru a redenumi tabelul DataFlair ??n DataFlair_Info?\n",3,
                " ALTER TABLE DataFlair SET TO DataFlair_Info."," ALTER TABLE DataFlair TO DataFlair_Info.",
                " ALTER TABLE DataFlair RENAME TO DataFlair_Info.", " ALTER TABLE DataFlair UPDATE TO DataFlair_Info.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre urm??toarele interog??ri este corect?? pentru a realiza unirea pe DataFlair ??i tabelele de loca??ii?\n",3,
                " Selecta??i col1, col2, col3 DIN DataFlair UNION Selecta??i col1, col2 DIN loca??ie."," Selecta??i numele, id FROM DataFlair UNION SELECT id, loca??ia FROM loca??ie.",
                " Selecta??i numele, id FROM Dataflair UNION Selecta??i loca??ia, id FROM loca??ie.", " Toate cele de mai sus.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre urm??toarele declan??atoare nu sunt disponibile pentru MySQL?\n",4,
                " Declan??are la nivel de r??nd."," Trigger la nivel de declara??ie.",
                " Ambele sunt disponibile.", " Nici unul dintre acestea.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care toate limbile accept?? integrarea SQL cu ele?\n",4,
                "java.","c++.",
                "pyton.", "toate acestea.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre interogarile de mai jos este folosit?? pentru a pune o valoare ??ntr-o variabil?? pe care trebuie s?? o definim pentru interogarea noastr?? SQL?\n",2,
                " Id = 6."," SET @id = 6.",
                " SET #id = 6.", " SET id = 6.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre urm??toarele este tipul de declan??ator disponibil ??n SQL?\n",4,
                " ??nainte de actualizare."," ??nainte de ??tergere.",
                " ??nainte de inserare.", " Toate acestea.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "??n ce element HTML punem JavaScript?",1,
                " script."," javascript.",
                " scripting.", " js.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Unde este locul corect pentru a introduce un JavaScript?",4,
                " In <head>."," In body.",
                "  Amandoua <<head>> sectiounea si tipul <<body>>.", " Amandoua <head> sectiounea si tipul <body>.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum scrie??i ???Hello World??? ??ntr-o caset?? de alert???",3,
                " alertBox(Hello World);."," msg(Hello World).",
                " alert(Hello World);.", " msgBox(Hello World).");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum se creeaz?? o func??ie ??n JavaScript?",1,
                " function myFunction().","function.myFunction().",
                " function:myFunction().", " function = myFunction().");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum apela??i o func??ie numit?? ???myFunction????",4,
                "call function myFunction()."," call myFunction().",
                " callmyFunction().", " myFunction().");
        myDb.insertDataQuestion(myQuestion);
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum se scrie o instruc??iune IF ??n JavaScript?",3,
                " if i = 5 then."," if i == 5 then.",
                " if (i == 5).", " if i = 5.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum se scrie o instruc??iune IF pentru executarea unui cod dac?? ???i??? NU este egal cu 5?",4,
                " if (i <> 5)."," if i <> 5.",
                "if (i != 5).", "if i =! 5 then.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum ??ncepe o bucl?? FOR?",2,
                " for (i = 0; i &lt;= 5)."," for (i = 0; i &lt;= 5; i++).",
                "for i = 1 to 5.", " for (i &lt;= 5; i++).");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum se insereaz?? un comentariu care are mai multe r??nduri?",1,
                " /*This comment has more than one line*/."," ///This comment has more than one line///.",
                " //This comment has more than one line//.", " <--This comment has more than one line-->.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum rotunji??i num??rul 7,25 la cel mai apropiat num??r ??ntreg?",2,
                " rnd(7.25)."," math.round(7.25).",
                " math.rnd(7.25).", " round(7.25).");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Ce eveniment are loc atunci c??nd utilizatorul face clic pe un element HTML?",2,
                "onchange."," onclick.",
                " onmouseclick.", " onmouseover.");
        myDb.insertDataQuestion(myQuestion);
    }
    public void insertTheory(){
        myTheory = new Theory(1,1,"Defini??ia conceptelor OOP ??n Java",false,"Ideile principale din spatele program??rii orientate pe obiecte din Java, conceptele OOP includ abstrac??ia , ??ncapsularea , mo??tenirea ??i polimorfismul . Practic, conceptele Java OOP ne permit s?? cre??m metode de lucru ??i variabile, apoi s?? reutilizam toate sau o parte din ele f??r?? a compromite securitatea. ??n??elegerea conceptelor OOP este cheia pentru ??n??elegerea modului ??n care func??ioneaz?? Java.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum func??ioneaz?? conceptele POO ??n Java",false,"Conceptele POO din Java func??ioneaz?? permi????nd programatorilor s?? creeze componente care sunt reutilizabile ??n moduri diferite, men??in??nd ??n acela??i timp securitatea.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum func??ioneaz?? abstrac??ia",false, "Abstrac??ia le permite programatorilor s?? creeze instrumente utile ??i reutilizabile. De exemplu, un programator poate crea mai multe tipuri diferite de obiecte , care pot fi variabile, func??ii sau structuri de date. De asemenea, programatorii pot crea diferite clase de obiecte ca modalit????i de definire a obiectelor.\n" +
                "\n" +
                "De exemplu, o clas?? de variabil?? poate fi o adres??. Clasa poate specifica c?? fiecare obiect de adres?? trebuie s?? aib?? un nume, o strad??, un ora?? ??i un cod po??tal. Obiectele, ??n acest caz, pot fi adrese ale angaja??ilor, adrese ale clien??ilor sau adrese ale furnizorilor.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum func??ioneaz?? ??ncapsularea",false, "??ncapsularea ne permite s?? reutilizam func??ionalitatea f??r?? a pune ??n pericol securitatea. Este un concept OOP puternic, care economise??te timp ??n Java. De exemplu, putem crea o bucat?? de cod care apeleaz?? date specifice dintr-o baz?? de date. Poate fi util s?? reutiliza??i acel cod cu alte baze de date sau procese. ??ncapsularea ne permite s?? facem asta ??n timp ce p??str??m datele noastre originale private. De asemenea, ne permite s?? ne modific??m codul original f??r?? a-l rupe pentru al??ii care l-au adoptat ??ntre timp.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum func??ioneaz?? mo??tenirea",false, "Mo??tenirea este un alt concept Java OOP care economise??te munc??, care func??ioneaz?? l??s??nd o nou?? clas?? s?? adopte propriet????ile alteia. Numim clasa mo??tenitoare o subclas?? sau o clas?? copil . Clasa original?? este adesea numit?? p??rinte . Folosim cuv??ntul cheie extins pentru a defini o nou?? clas?? care mo??tene??te propriet????i de la o clas?? veche.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum func??ioneaz?? polimorfismul",false, "Polimorfismul ??n Java func??ioneaz?? prin utilizarea unei referin??e la o clas?? p??rinte pentru a afecta un obiect din clasa copil. Am putea crea o clas?? numit?? ???cal??? prin extinderea clasei ???animal???. Aceast?? clas?? ar putea implementa ??i clasa ???curse profesionale???. Clasa ???cal??? este ???polimorf?????, deoarece mo??tene??te atributele clasei ???animal??? ??i ???curse profesionale???.\n" +
                "\n" +
                "??nc?? dou?? exemple de polimorfism ??n Java sunt suprascrierea metodei ??i supra??nc??rcarea metodei.\n" +
                "\n" +
                "??n suprascrierea metodei , clasa copil poate folosi conceptul de polimorfism OOP pentru a suprascrie o metod?? a clasei p??rinte. Acest lucru permite unui programator s?? foloseasc?? o metod?? ??n moduri diferite, ??n func??ie de faptul dac?? este invocat?? de un obiect al clasei p??rinte sau de un obiect al clasei copil.\n" +
                "\n" +
                "??n supra??nc??rcarea metodei, o singur?? metod?? poate ??ndeplini diferite func??ii ??n func??ie de contextul ??n care este apelat??. Aceasta ??nseamn?? c?? un singur nume de metod?? poate func??iona ??n moduri diferite, ??n func??ie de argumentele care ??i sunt transmise.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Quiz",false, "1");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Recapitulare",true, "");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2," Ce este SQL?",false,"Structured Query Language (SQL) se refer?? la un limbaj de programare standard utilizat pentru extragerea, organizarea, gestionarea ??i manipularea datelor stocate ??n baze de date rela??ionale. Prin urmare, SQL este denumit un limbaj de baz?? de date care poate executa activit????i pe baze de date care constau din tabele alc??tuite din r??nduri ??i coloane.\n" +
                "\n" +
                "SQL joac?? un rol crucial ??n preluarea datelor relevante din bazele de date, care pot fi utilizate ulterior de diverse platforme precum Python sau R ??n scopuri de analiz??. SQL poate gestiona mai multe tranzac??ii de date simultan, unde volume mari de date sunt scrise concomitent.\n" +
                "\n" +
                "SQL este un standard American National Standards Institute (ANSI) care func??ioneaz?? prin mai multe versiuni ??i cadre pentru a gestiona date backend prin diverse aplica??ii web sus??inute de baze de date rela??ionale , cum ar fi MySQL, SQL Server, Oracle PostgreSQL ??i altele.\n" +
                "\n" +
                "Companiile de top de??inute de Meta Inc., cum ar fi Facebook, WhatsApp ??i Instagram , se bazeaz?? toate pe SQL pentru procesarea datelor ??i stocarea backend.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2," Cum func??ioneaz?? SQL? ",false,"Pe m??sur?? ce o interogare SQL este scris?? ??i rulat??, aceasta este procesat?? de ???procesorul limbajului de interogare??? care are un parser ??i un optimizator de interog??ri. Serverul SQL compileaz?? apoi interogarea procesat?? ??n trei etape:\n" +
                "\n" +
                "1. Analiza:\n" +
                "\n" +
                "2. Legare:\n" +
                "\n" +
                "3. Optimizare:");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Beneficii esen??iale SQL",false, "SQL ofer?? mai multe beneficii, deoarece este un limbaj u??or de utilizat, accesibil pe toate platformele.??\n" +
                "\n" +
                "beneficiile cheie ale SQL\n" +
                "\n" +
                "    Limbaj portabil:\n" +
                "    Procesare rapid?? a interog??rilor:\n" +
                "    Nu sunt necesare abilit????i de codare:\n" +
                "    Platform?? uniform?? cu limbaj standardizat\n" +
                "    Ofer?? mai multe vizualiz??ri de date:\n" +
                "    Cod surs?? deschis\n" +
                "    Principalii furnizori de sisteme de management al bazelor de date (DBMS) folosesc SQL IBM, Oracle, Microsoft\n" +
                "    Limbaj interactiv");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2," Elemente de SQL ",false, "SQL este alegerea majorit????ii utilizatorilor de baze de date datorit?? utiliz??rii u??oare ??i modului ??n care interog??rile pot ??ndeplini func??ii variate pe cantit????i mari de date structurate.\n" +
                "\n" +
                "Limbajul de programare SQL are urm??toarele elemente vitale:\n" +
                "\n" +
                "1. Cuvinte cheie\n" +
                "\n" +
                "2. Clauze\n" +
                "\n" +
                "3. Expresii\n" +
                "\n" +
                "4. Predicate\n" +
                "\n" +
                "5. Interog??ri");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Cum func??ioneaz?? mo??tenirea",false, "Mo??tenirea este un alt concept Java OOP care economise??te munc??, care func??ioneaz?? l??s??nd o nou?? clas?? s?? adopte propriet????ile alteia. Numim clasa mo??tenitoare o subclas?? sau o clas?? copil . Clasa original?? este adesea numit?? p??rinte . Folosim cuv??ntul cheie extins pentru a defini o nou?? clas?? care mo??tene??te propriet????i de la o clas?? veche.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Elemente de SQL",false, "1. Cuvinte cheie: LIKE\n" +
                "\n" +
                "De exemplu, s?? presupunem c?? trebuie s?? identific??m numele familiilor care tr??iesc ??n zona Boston care au drept nume de familie ???Luis???. Urm??toarea interogare SQL va aduce rezultatele relevante pentru aceast?? declara??ie de problem??:\n" +
                "\n" +
                "SELECTA??I * DIN [BOSTON] UNDE NUME CA ???%LUIS???\n" +
                "\n" +
                "??n mod similar, diferite cuvinte cheie efectueaz?? diverse opera??ii asupra bazelor de date. Urm??toarele sunt c??teva exemple de astfel de cuvinte cheie cu rolurile lor func??ionale:\n" +
                "\n" +
                "    CREA:\n" +
                "    INTRODUCE:\n" +
                "    SELECTA??I:\n" +
                "    DIN:\n" +
                "    UNDE:\n" +
                "    ACTUALIZA??I:\n" +
                "    ??TERGE:\n" +
                "\n" +
                "2. Clauze:\n" +
                "\n" +
                "???SELECT V??rsta, E-mail, Adres?????\n" +
                "\n" +
                "3. Expresii: boolean, numeric, data\n" +
                "\n" +
                "S?? lu??m ??n considerare un exemplu de expresie boolean?? care preia date prin potrivirea unor valori individuale. Dac?? dori??i s?? identifica??i angaja??ii ale c??ror salarii sunt egale cu 5.000, pute??i utiliza urm??toarea interogare SQL:??\n" +
                "\n" +
                "SELECTA??I * DIN ANGAJA??II UNDE SALARIU = 5000;\n" +
                "\n" +
                "4. Predicate:\n" +
                "\n" +
                "De exemplu, lua??i ??n considerare urm??toarea instruc??iune SQL:\n" +
                "\n" +
                "SELECTA??I * FROM CLIENTS WHERE Produs = ???Televizor???\n" +
                "\n" +
                "Aici, ???Produs = Televiziune??? este predicatul instruc??iunii SQL.\n" +
                "\n" +
                "5. ??ntreb??ri:\n" +
                "\n" +
                "SELECT First_Name, Customer_No FROM Customers WHERE Last_Name='Lobo';");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Quiz",false, "1");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Recapitulare",true, "");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Ce este Web-ul?",false, "Web-ul este un subset al internetului.\n" +
                "\n" +
                "Ca orice alt?? re??ea de calculatoare, Web-ul este alc??tuit din dou?? componente principale: clientul browserului web ??i serverul web.\n" +
                "\n" +
                "Clientul solicit?? datele ??i serverul partajeaz?? sau serve??te datele acestuia. Pentru a realiza acest lucru, cele dou?? p??r??i trebuie s?? ??ncheie un acord. Acel acord se nume??te Interfa???? de programare a aplica??iilor sau, pe scurt, API.\n" +
                "\n" +
                "Dar aceste date trebuie aranjate ??i formatate ??ntr-o form?? care s?? fie ??n??eleas?? de utilizatorii finali care au o gam?? larg?? de experien??e ??i abilit????i tehnice.\n" +
                "\n" +
                "Aici intr?? ??n joc HTML, CSS, JavaScript ??i ??ntregul concept de dezvoltare web.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Ce este HTML?",false, "A??adar, v?? pute??i g??ndi la HTML ca limbajul folosit pentru a crea instruc??iuni detaliate privind stilul, tipul, formatul, structura ??i alc??tuirea unei pagini web ??nainte de a fi tip??rit?? (v?? ar??ta).\n" +
                "\n" +
                "Dar, ??n contextul dezvolt??rii web, putem ??nlocui termenul ???tip??rit??? cu ???redat??? ca termen mai precis.\n" +
                "\n" +
                "HTML v?? ajut?? s?? v?? structura??i pagina ??n elemente precum paragrafe, sec??iuni, titluri, bare de navigare ??i a??a mai departe.  \n" +
                "\n" +
                "Pentru a ilustra cum arat?? o pagin??, s?? cre??m un document HTML de baz??:\n" +
                "\n" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "  <meta charset=\"UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "  <link rel=\"stylesheet\" href=\"./styles.css\">\n" +
                "  <title>Document</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "  <h1>This is a first level heading in HTML. With CSS, I will turn this into red color</h1>\n" +
                "  <h2>This is a second level heading in HTML. With CSS, I will turn this into blue color</h2>\n" +
                "  <h3>This is a third level heading in HTML. With CSS, I will turn this into green color</h3>\n" +
                "  <p>This is a <em>paragragh</em> As you can see, I placed an empahisis on the word \"paragraph\". Now, I will change also\n" +
                "    the background color of the word \"paragraph\" to black, and its text color  to green, all with just CSS.</p>\n" +
                "  <p>The main essence of this tutorial is to:</p>\n" +
                "    <ul>\n" +
                "       <li>Show you how to format a web document with HTML</li>\n" +
                "       <li>Show you how to design a web page with CSS</li>\n" +
                "       <li>Show you how to program a web document with JavaScript</li>\n" +
                "    </ul>\n" +
                "\n" +
                "  <p>Next, I am going to add the following two numbers and display the result, all with JavaScript<p/>\n" +
                "    <p>First number:<span id= \"firstNum\">2</span> <br></p>\n" +
                "    <p>Second number: <span id= \"secondNum\">7</span> </p>\n" +
                "    <p>Therefore, the sum of the two of those numbers is: <span id= \"answer\">(placeholder for the answer)</span></p>\n" +
                "    <input type=\"button\" id=\"sumButton\" value=\"Click to add!\">\n" +
                "</body>\n" +
                "</html>\n" +
                "\n" +
                "index.html\n" +
                "\n" +
                "Acesta este modul ??n care pute??i formata ??i structura un document doar cu HTML. Dup?? cum pute??i vedea, acest marcaj con??ine c??teva elemente web, cum ar fi:\n" +
                "\n" +
                "    Titlu de nivel 1h1\n" +
                "    Titlu de nivel 2h2\n" +
                "    Titlul de nivel 3h3\n" +
                "    Un paragraf  p\n" +
                "    O list?? neordonat?? cu puncte  ul li\n" +
                "    Un buton de intrareinput\n" +
                "    ??i tot corpul paginiibody\n" +
                "\n" +
                "Iat?? ce red?? markupul de mai sus pe un browser web:\n" +
                "HTML\n" +
                "localhost:3000/index.html\n" +
                "\n" +
                "De asemenea, pute??i ad??uga atribute acestor elemente pe care le pute??i utiliza pentru a identifica elementele ??i a le accesa din alte locuri din site.\n" +
                "\n" +
                "??n exemplul nostru, set??m idatributele tuturor celor trei spanelemente. Acest lucru ne va ajuta s?? le acces??m din JavaScript, a??a cum ve??i vedea mai t??rziu.\n" +
                "\n" +
                "G??ndi??i-v?? la acest atribut la fel ca numele de utilizator al re??elelor sociale. Cu acest nume, al??ii te pot g??si pe re??elele sociale. ??i cineva se poate referi la tine sau te poate men??iona cu acest nume (po??i fi etichetat ??ntr-o postare ??i a??a mai departe).\n" +
                "\n" +
                "Aceast?? pagin?? este totu??i foarte simpl?? ??i neatr??g??toare. Dac?? construi??i altceva dec??t un demo, va trebui s?? ad??uga??i un stil de baz?? pentru a-l face mai prezentabil. ??i putem face exact asta cu CSS.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Ce este CSS?",false, "??n timp ce HTML este un limbaj de marcare folosit pentru a formata/structura o pagin?? web, CSS este un limbaj de design pe care ??l utiliza??i pentru a face pagina dvs. web s?? arate frumos ??i prezentabil.\n" +
                "\n" +
                "CSS ??nseamn?? Cascading Style Sheets ??i ??l folosi??i pentru a ??mbun??t????i aspectul unei pagini web. Ad??ug??nd stiluri CSS bine g??ndite, v?? face??i pagina mai atractiv?? ??i mai pl??cut?? de vizualizat ??i utilizat de utilizatorul final.\n" +
                "\n" +
                "Imagineaz??-??i dac?? fiin??ele umane ar fi f??cute doar pentru a avea schelete ??i oase goale - cum ar ar??ta asta? Nu e frumos dac?? m?? ??ntrebi pe mine. Deci CSS este ca pielea, p??rul ??i aspectul fizic general.\n" +
                "\n" +
                "De asemenea, pute??i utiliza CSS pentru a dispune elemente, pozi??ion??ndu-le ??n zonele specificate ale paginii dvs.\n" +
                "\n" +
                "Pentru a accesa aceste elemente, trebuie s?? le ???selecta??i???. Pute??i selecta unul sau mai multe elemente web ??i s?? specifica??i cum dori??i s?? arate sau s?? fie pozi??ionate.\n" +
                "\n" +
                "Regulile care guverneaz?? acest proces se numesc selectoare CSS .\n" +
                "\n" +
                "Cu CSS pute??i seta culoarea ??i fundalul elementelor dvs., precum ??i fontul, marginile, spa??ierea, umplutura ??i multe altele.\n" +
                "\n" +
                "Dac?? v?? aminti??i pagina noastr?? HTML de exemplu, aveam elemente care se explicau de la sine. De exemplu, am afirmat c?? voi schimba culoarea titlului nivelului unu h1??n ro??u.\n" +
                "\n" +
                "Pentru a ilustra modul ??n care func??ioneaz?? CSS, voi ??mp??rt????i codul care seteaz?? culoarea de fundal a celor trei niveluri de antete la ro??u, albastru ??i, respectiv, verde:\n" +
                "\n" +
                "h1 {\n" +
                "  background-color: #ff0000;\n" +
                "}\n" +
                "\n" +
                "h2 {\n" +
                "  background-color: #0000FF;\n" +
                "}\n" +
                "\n" +
                "h3 {\n" +
                "  background-color: #00FF00;\n" +
                "}\n" +
                "\n" +
                "em {\n" +
                "  background-color: #000000;\n" +
                "  color: #ffffff;\n" +
                "}\n" +
                "\n" +
                "localhost:3000/styles.css\n" +
                "\n" +
                "Stilul de mai sus, atunci c??nd este aplicat, va schimba aspectul paginii noastre web ??n acesta:\n" +
                "CSS\n" +
                "\n" +
                "Cool, nu?\n" +
                "\n" +
                "Acces??m fiecare dintre elementele la care dorim s?? lucr??m ???select??ndu-le???. Selecteaz?? toate titlurile de h1nivelul 1 din pagin??, h2selecteaz?? elementele de nivelul 2 ??i a??a mai departe. Pute??i selecta orice element HTML dorit ??i s?? specifica??i cum dori??i s?? arate sau s?? fie pozi??ionat.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Ce este JavaScript?",false, "Acum, dac?? HTML este limbajul de marcare ??i CSS este limbajul de proiectare , atunci JavaScript este limbajul de programare.\n" +
                "\n" +
                "Dac?? nu ??tii ce este programarea, g??nde??te-te la anumite ac??iuni pe care le faci ??n via??a de zi cu zi:\n" +
                "\n" +
                "C??nd sim??i pericolul, fugi. C??nd ??i-e foame, m??n??nci. C??nd e??ti obosit, dormi. C??nd ??i-e frig, cau??i c??ldur??. C??nd traversa??i un drum aglomerat, calcula??i distan??a dintre vehicule distan???? de dvs.\n" +
                "\n" +
                "Creierul t??u a fost programat s?? reac??ioneze ??ntr-un anumit mod sau s?? fac?? anumite lucruri ori de c??te ori se ??nt??mpl?? ceva. ??n acela??i mod, ????i po??i programa pagina web sau elementele individuale s?? reac??ioneze ??ntr-un anumit fel ??i s?? fac?? ceva atunci c??nd se ??nt??mpl?? altceva (un eveniment).\n" +
                "\n" +
                "Pute??i programa ac??iuni, condi??ii, calcule, solicit??ri de re??ea, sarcini concurente ??i multe alte tipuri de instruc??iuni.\n" +
                "\n" +
                "Pute??i accesa orice elemente prin API-ul Document Object Model (DOM) ??i le pute??i modifica oricum dori??i.\n" +
                "\n" +
                "DOM-ul este o reprezentare arborescent?? a paginii web care se ??ncarc?? ??n browser.\n" +
                "DOM-\n" +
                "Fiecare element de pe pagina web este reprezentat pe DOM\n" +
                "\n" +
                "Datorit?? DOM, putem folosi metode precum getElementById()accesarea elementelor din pagina noastr?? web.\n" +
                "\n" +
                "JavaScript v?? permite s?? v?? face??i pagina web ???s?? g??ndeasc?? ??i s?? ac??ioneze??? , ceea ce ??nseamn?? programare.\n" +
                "\n" +
                "Dac?? v?? aminti??i din pagina noastr?? HTML de exemplu, am men??ionat c?? urma s?? ??nsumez cele dou?? numere afi??ate pe pagin?? ??i apoi s?? afi??ez rezultatul ??n locul textului substituent. Calculul ruleaz?? odat?? ce butonul este ap??sat.\n" +
                "CSS-1\n" +
                "F??c??nd clic pe butonul ???Ob??ine??i suma??? va afi??a suma 2 ??i 7\n" +
                "\n" +
                "Acest cod ilustreaz?? cum pute??i face calcule cu JavaScript:\n" +
                "\n" +
                "function displaySum() {\n" +
                "  let firstNum = Number(document.getElementById('firstNum').innerHTML)\n" +
                "  let secondNum = Number(document.getElementById('secondNum').innerHTML)\n" +
                "\n" +
                "  let total = firstNum + secondNum;\n" +
                "  document.getElementById(\"answer\").innerHTML = ` ${firstNum} + ${secondNum}, equals to ${total}` ;\n" +
                "}\n" +
                "\n" +
                "document.getElementById('sumButton').addEventListener(\"click\", displaySum);\n" +
                "\n" +
                "V?? aminti??i ce v-am spus despre atributele HTML ??i despre utiliz??rile lor? Acest cod afi??eaz?? tocmai asta.\n" +
                "\n" +
                "Este displaySumo func??ie care preia ambele elemente de pe pagina web, le converte??te ??n numere (cu metoda Number), le ??nsumeaz?? ??i le transmite ca valori interioare unui alt element.\n" +
                "\n" +
                "Motivul pentru care am putut accesa aceste elemente ??n JavaScript a fost pentru c?? le-am setat atribute unice, pentru a ne ajuta s?? le identific??m.\n" +
                "\n" +
                "Deci, datorit?? acestui lucru:\n" +
                "\n" +
                "// id attribute has been set in all three\n" +
                "\n" +
                "<span id= \"firstNum\">2</span> <br> \n" +
                "    ...<span id= \"secondNum\">7</span> \n" +
                "    ...... <span id= \"answer\">(placeholder for the answer)</span>\n" +
                "\n" +
                "Am putut face asta:\n" +
                "\n" +
                "//getElementById will get all HTML elements by a specific \"id\" attribute\n" +
                "...\n" +
                "let firstNum = Number(document.getElementById('firstNum').innerHTML)\n" +
                "  let secondNum = Number(document.getElementById('secondNum').innerHTML)\n" +
                "\n" +
                "  let total = firstNum + secondNum;\n" +
                "  document.getElementById(\"answer\").innerHTML = ` ${firstNum} + ${secondNum}, equals to ${total}` ;\n" +
                "\n" +
                "??n cele din urm??, la ap??sarea butonului, ve??i vedea suma celor dou?? numere pe pagina nou actualizat??:\n" +
                "JAVASCRIPT\n" +
                "2 plus 7 este egal cu 9\n");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Cum s?? pune??i ??mpreun?? HTML, CSS ??i JavaScript",false, "??mpreun??, folosim aceste trei limbi pentru a formata, proiecta ??i programa pagini web.\n" +
                "\n" +
                "??i atunci c??nd conecta??i unele pagini web cu hyperlinkuri, ??mpreun?? cu toate activele lor, cum ar fi imagini, videoclipuri ??i a??a mai departe, care se afl?? pe computerul server, acesta este redat ??ntr-un site web .\n" +
                "\n" +
                "Aceast?? redare are loc de obicei pe front-end, unde utilizatorii pot vedea ceea ce este afi??at ??i pot interac??iona cu acesta.\n" +
                "\n" +
                "Pe de alt?? parte, datele, ??n special informa??iile sensibile precum parolele, sunt stocate ??i furnizate din partea de back-end a site-ului web. Aceasta este partea unui site web care exist?? doar pe computerul server ??i nu este afi??at?? pe browserul front-end. Acolo, utilizatorul nu poate vedea sau accesa cu u??urin???? acele informa??ii.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Quiz",false, "1");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Recapitulare",true, "");
        myDb.insertDataTheory(myTheory);
    }
    public void insertCourses(){
        myCourse = new Course(1,
                "Conceptele Java de programare orientat?? pe obiecte, POO formeaz?? coloana vertebral?? a program??rii Java.", false);
        /*
        if (myDb.insertDataCourse(myCourse)) {
            Toast.makeText(getBaseContext(), "Courses Register!", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getBaseContext(), "1111111!", Toast.LENGTH_SHORT).show();
         */
        myDb.insertDataCourse(myCourse);
        myCourse = new Course(2,"SQL este un limbaj standardizat folosit pentru a interac??iona cu bazele de date rela??ionale.", false);
        myDb.insertDataCourse(myCourse);
        myCourse = new Course(3,"Afla??i elementele de baz?? ale dezvolt??rii web ??? HTML, CSS ??i JavaScript explicate pentru ??ncep??tori", false);
        myDb.insertDataCourse(myCourse);
    }
}