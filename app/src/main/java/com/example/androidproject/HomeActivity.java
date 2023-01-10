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
        myQuestion = new Question(1,1,"Selectați opțiunea(ele) corectă(e) despre obiectul clasei.",4,
                "Clasa Object este o superclasă a tuturor celorlalte clase.","O variabilă de tip Object poate deține referință la orice obiect sau o referință nulă",
                "Trebuie să extindeți în mod explicit clasa Object.","Toate optiunile.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Doriți doar să declarați comportamentul unui anumit tip fără nicio implementare, ar trebui să îl declarați ca interfață. package com.vq.inheritance;\n" +
                "\n" +
                "public interface VQCourse {\n" +
                "\n" +
                "    public void printCourseDetail();\n" +
                "\n" +
                "    public void getCoursePrice();\n" +
                "\n" +
                "}",2,
                "VQJavaCourse extends VQCourse","VQJavaCourse implements VQCourse",
                "Sintaxă greșită.","VQJavaCourse");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Java a furnizat mecanism prin care clasele pot moșteni starea și comportamentul utilizat în mod obișnuit al altor clase. public class VQJavaCourse extends VQCourse {\n" +
                "//Some implementation \n" +
                "}",1,
                "Class VQJavaCourse inherits class VQCourse","VQCourse is a interface.",
                "Sintaxă greșită.","VQCourse inherit ");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Ce este un obiect?",4,
                "Fiecare obiect are un tip.","Un obiect poate fi construit folosind alte obiecte.",
                "Un program java constă din obiecte care interacționează între ele prin trimiterea de mesaje..","Toate optiunile.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Java este un limbaj de programare orientat pe obiecte în care declarați clasa care este tipul de obiect pe care tocmai l-ați identificat. Obiectele au stări și comportamente care sunt metode în clasă.",4,
                "Clasa Object este o superclasă a tuturor celorlalte clase.","O variabilă de tip Object poate deține referință la orice obiect sau o referință nulă",
                "Trebuie să extindeți în mod explicit clasa Object.","Toate optiunile.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1,"Urmează sintaxa pentru a crea un obiect din clasa Object. Ce declarații sunt adevărate atunci când următoarea instrucțiune este executată în timpul execuției.\n" +
                "\n" +
                "\n" +
                "Object obj = nou Object();",3,
                "S-a creat o nouă instanță a clasei Object.","Memorie alocată pentru obiectul nou, dacă este disponibilă.",
                "Toate optiunile.", "Obj de referință de tip Object este atribuit unei noi instanțe a obiectului creat.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1, "Următorul program are o eroare de compilare, puteți ghici cauza principală a acesteia?abstract class VQCourse {\n" +
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
                "Protejat înseamnă că numai clasa și clasa moștenite au acces la membrii protejați.","Declarați clasa VQCourse ca finală.",
                "Nu puteți instanția o clasă abstractă.", "Nici unul");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,1, "Care ar fi rezultatul corect al programului următor?class VQCourse {\n" +
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
        myQuestion = new Question(1,1, "Codul de mai jos are clasa VQClassB care moștenește o altă clasă VQClassA, studiați cu atenție codul și alegeți opțiunea corectă despre ieșirea programului.class VQClassA {\n" +
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
        myQuestion = new Question(1,1, "Codul de mai jos are clasa VQClassB care moștenește o altă clasă VQClassA, studiați cu atenție codul și alegeți opțiunea corectă despre ieșirea programului.public class VQClassB {\n" +
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
        myQuestion = new Question(1,2, "Care este forma completă de SQL?",1,
                " Structured Query Language."," Structured Question Language.",
                " Structural Query Language.", " Structuring Query Language.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Câte chei primare pot fi create pe un tabel?",3,
                "o infiniatet.","2.",
                "1.", "25.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre următoarele nu este o clauză în SQL?",1,
                "Below.","limit.",
                "where.", "group by.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre următoarele interogări ar limita rezultatul la 5 rânduri?",2,
                " SELECT * FROM DataFlair 5."," SELECT * FROM DataFlair LIMIT 5.",
                " SELECT * FROM DataFlair 5 LIMIT.", " SELECT LIMIT(5) * FROM DataFlair.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, " Care dintre următoarele interogări descrie instrucțiunea Select corectă?",2,
                " Select # From DataFlair."," Select * From DataFlair.",
                " Select From DataFlair.", " Select col1,col2 From.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care sunt comenzile de definire a datelor în SQL cunoscute ca popular?\n",4,
                "DML.","UML.",
                "RDEMS.", "DDL.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "În SQL definiția pachetului este alcătuită din care două elemente?\n",2,
                "Definițiile pachetelor și variabilele pachetului."," Corpul pachetului și specificațiile pachetului.",
                " Biblioteci de pachete și coduri de pachete.", " Nici una dintre cele de mai sus.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre următoarele interogări, dacă este rulată, va trunchia tabelul DataFlair?\n",2,
                " TRUNCATE DataFlair."," TRUNCATE TABLE DataFlair.",
                " TRUNCATE TABLE.", " Nici unul dintre acestea.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Scrieți o interogare în SQL folosind doar două cuvinte pentru a extrage ora sistemului de acum?\n",3,
                " SELECT RIGHTNOW()."," SELECT CURR().",
                " SELECT NOW().", " SELECT TIME().");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre interogările de mai jos pot fi folosite pentru a redenumi tabelul DataFlair în DataFlair_Info?\n",3,
                " ALTER TABLE DataFlair SET TO DataFlair_Info."," ALTER TABLE DataFlair TO DataFlair_Info.",
                " ALTER TABLE DataFlair RENAME TO DataFlair_Info.", " ALTER TABLE DataFlair UPDATE TO DataFlair_Info.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre următoarele interogări este corectă pentru a realiza unirea pe DataFlair și tabelele de locații?\n",3,
                " Selectați col1, col2, col3 DIN DataFlair UNION Selectați col1, col2 DIN locație."," Selectați numele, id FROM DataFlair UNION SELECT id, locația FROM locație.",
                " Selectați numele, id FROM Dataflair UNION Selectați locația, id FROM locație.", " Toate cele de mai sus.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre următoarele declanșatoare nu sunt disponibile pentru MySQL?\n",4,
                " Declanșare la nivel de rând."," Trigger la nivel de declarație.",
                " Ambele sunt disponibile.", " Nici unul dintre acestea.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care toate limbile acceptă integrarea SQL cu ele?\n",4,
                "java.","c++.",
                "pyton.", "toate acestea.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre interogarile de mai jos este folosită pentru a pune o valoare într-o variabilă pe care trebuie să o definim pentru interogarea noastră SQL?\n",2,
                " Id = 6."," SET @id = 6.",
                " SET #id = 6.", " SET id = 6.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,2, "Care dintre următoarele este tipul de declanșator disponibil în SQL?\n",4,
                " Înainte de actualizare."," Înainte de ștergere.",
                " Înainte de inserare.", " Toate acestea.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "În ce element HTML punem JavaScript?",1,
                " script."," javascript.",
                " scripting.", " js.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Unde este locul corect pentru a introduce un JavaScript?",4,
                " In <head>."," In body.",
                "  Amandoua <<head>> sectiounea si tipul <<body>>.", " Amandoua <head> sectiounea si tipul <body>.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum scrieți „Hello World” într-o casetă de alertă?",3,
                " alertBox(Hello World);."," msg(Hello World).",
                " alert(Hello World);.", " msgBox(Hello World).");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum se creează o funcție în JavaScript?",1,
                " function myFunction().","function.myFunction().",
                " function:myFunction().", " function = myFunction().");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum apelați o funcție numită „myFunction”?",4,
                "call function myFunction()."," call myFunction().",
                " callmyFunction().", " myFunction().");
        myDb.insertDataQuestion(myQuestion);
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum se scrie o instrucțiune IF în JavaScript?",3,
                " if i = 5 then."," if i == 5 then.",
                " if (i == 5).", " if i = 5.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum se scrie o instrucțiune IF pentru executarea unui cod dacă „i” NU este egal cu 5?",4,
                " if (i <> 5)."," if i <> 5.",
                "if (i != 5).", "if i =! 5 then.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum începe o buclă FOR?",2,
                " for (i = 0; i &lt;= 5)."," for (i = 0; i &lt;= 5; i++).",
                "for i = 1 to 5.", " for (i &lt;= 5; i++).");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum se inserează un comentariu care are mai multe rânduri?",1,
                " /*This comment has more than one line*/."," ///This comment has more than one line///.",
                " //This comment has more than one line//.", " <--This comment has more than one line-->.");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Cum rotunjiți numărul 7,25 la cel mai apropiat număr întreg?",2,
                " rnd(7.25)."," math.round(7.25).",
                " math.rnd(7.25).", " round(7.25).");
        myDb.insertDataQuestion(myQuestion);
        myQuestion = new Question(1,3, "Ce eveniment are loc atunci când utilizatorul face clic pe un element HTML?",2,
                "onchange."," onclick.",
                " onmouseclick.", " onmouseover.");
        myDb.insertDataQuestion(myQuestion);
    }
    public void insertTheory(){
        myTheory = new Theory(1,1,"Definiția conceptelor OOP în Java",false,"Ideile principale din spatele programării orientate pe obiecte din Java, conceptele OOP includ abstracția , încapsularea , moștenirea și polimorfismul . Practic, conceptele Java OOP ne permit să creăm metode de lucru și variabile, apoi să reutilizam toate sau o parte din ele fără a compromite securitatea. Înțelegerea conceptelor OOP este cheia pentru înțelegerea modului în care funcționează Java.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum funcționează conceptele POO în Java",false,"Conceptele POO din Java funcționează permițând programatorilor să creeze componente care sunt reutilizabile în moduri diferite, menținând în același timp securitatea.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum funcționează abstracția",false, "Abstracția le permite programatorilor să creeze instrumente utile și reutilizabile. De exemplu, un programator poate crea mai multe tipuri diferite de obiecte , care pot fi variabile, funcții sau structuri de date. De asemenea, programatorii pot crea diferite clase de obiecte ca modalități de definire a obiectelor.\n" +
                "\n" +
                "De exemplu, o clasă de variabilă poate fi o adresă. Clasa poate specifica că fiecare obiect de adresă trebuie să aibă un nume, o stradă, un oraș și un cod poștal. Obiectele, în acest caz, pot fi adrese ale angajaților, adrese ale clienților sau adrese ale furnizorilor.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum funcționează încapsularea",false, "Încapsularea ne permite să reutilizam funcționalitatea fără a pune în pericol securitatea. Este un concept OOP puternic, care economisește timp în Java. De exemplu, putem crea o bucată de cod care apelează date specifice dintr-o bază de date. Poate fi util să reutilizați acel cod cu alte baze de date sau procese. Încapsularea ne permite să facem asta în timp ce păstrăm datele noastre originale private. De asemenea, ne permite să ne modificăm codul original fără a-l rupe pentru alții care l-au adoptat între timp.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum funcționează moștenirea",false, "Moștenirea este un alt concept Java OOP care economisește muncă, care funcționează lăsând o nouă clasă să adopte proprietățile alteia. Numim clasa moștenitoare o subclasă sau o clasă copil . Clasa originală este adesea numită părinte . Folosim cuvântul cheie extins pentru a defini o nouă clasă care moștenește proprietăți de la o clasă veche.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Cum funcționează polimorfismul",false, "Polimorfismul în Java funcționează prin utilizarea unei referințe la o clasă părinte pentru a afecta un obiect din clasa copil. Am putea crea o clasă numită „cal” prin extinderea clasei „animal”. Această clasă ar putea implementa și clasa „curse profesionale”. Clasa „cal” este „polimorfă”, deoarece moștenește atributele clasei „animal” și „curse profesionale”.\n" +
                "\n" +
                "Încă două exemple de polimorfism în Java sunt suprascrierea metodei și supraîncărcarea metodei.\n" +
                "\n" +
                "În suprascrierea metodei , clasa copil poate folosi conceptul de polimorfism OOP pentru a suprascrie o metodă a clasei părinte. Acest lucru permite unui programator să folosească o metodă în moduri diferite, în funcție de faptul dacă este invocată de un obiect al clasei părinte sau de un obiect al clasei copil.\n" +
                "\n" +
                "În supraîncărcarea metodei, o singură metodă poate îndeplini diferite funcții în funcție de contextul în care este apelată. Aceasta înseamnă că un singur nume de metodă poate funcționa în moduri diferite, în funcție de argumentele care îi sunt transmise.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Quiz",false, "1");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,1,"Recapitulare",true, "");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2," Ce este SQL?",false,"Structured Query Language (SQL) se referă la un limbaj de programare standard utilizat pentru extragerea, organizarea, gestionarea și manipularea datelor stocate în baze de date relaționale. Prin urmare, SQL este denumit un limbaj de bază de date care poate executa activități pe baze de date care constau din tabele alcătuite din rânduri și coloane.\n" +
                "\n" +
                "SQL joacă un rol crucial în preluarea datelor relevante din bazele de date, care pot fi utilizate ulterior de diverse platforme precum Python sau R în scopuri de analiză. SQL poate gestiona mai multe tranzacții de date simultan, unde volume mari de date sunt scrise concomitent.\n" +
                "\n" +
                "SQL este un standard American National Standards Institute (ANSI) care funcționează prin mai multe versiuni și cadre pentru a gestiona date backend prin diverse aplicații web susținute de baze de date relaționale , cum ar fi MySQL, SQL Server, Oracle PostgreSQL și altele.\n" +
                "\n" +
                "Companiile de top deținute de Meta Inc., cum ar fi Facebook, WhatsApp și Instagram , se bazează toate pe SQL pentru procesarea datelor și stocarea backend.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2," Cum funcționează SQL? ",false,"Pe măsură ce o interogare SQL este scrisă și rulată, aceasta este procesată de „procesorul limbajului de interogare” care are un parser și un optimizator de interogări. Serverul SQL compilează apoi interogarea procesată în trei etape:\n" +
                "\n" +
                "1. Analiza:\n" +
                "\n" +
                "2. Legare:\n" +
                "\n" +
                "3. Optimizare:");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Beneficii esențiale SQL",false, "SQL oferă mai multe beneficii, deoarece este un limbaj ușor de utilizat, accesibil pe toate platformele. \n" +
                "\n" +
                "beneficiile cheie ale SQL\n" +
                "\n" +
                "    Limbaj portabil:\n" +
                "    Procesare rapidă a interogărilor:\n" +
                "    Nu sunt necesare abilități de codare:\n" +
                "    Platformă uniformă cu limbaj standardizat\n" +
                "    Oferă mai multe vizualizări de date:\n" +
                "    Cod sursă deschis\n" +
                "    Principalii furnizori de sisteme de management al bazelor de date (DBMS) folosesc SQL IBM, Oracle, Microsoft\n" +
                "    Limbaj interactiv");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2," Elemente de SQL ",false, "SQL este alegerea majorității utilizatorilor de baze de date datorită utilizării ușoare și modului în care interogările pot îndeplini funcții variate pe cantități mari de date structurate.\n" +
                "\n" +
                "Limbajul de programare SQL are următoarele elemente vitale:\n" +
                "\n" +
                "1. Cuvinte cheie\n" +
                "\n" +
                "2. Clauze\n" +
                "\n" +
                "3. Expresii\n" +
                "\n" +
                "4. Predicate\n" +
                "\n" +
                "5. Interogări");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Cum funcționează moștenirea",false, "Moștenirea este un alt concept Java OOP care economisește muncă, care funcționează lăsând o nouă clasă să adopte proprietățile alteia. Numim clasa moștenitoare o subclasă sau o clasă copil . Clasa originală este adesea numită părinte . Folosim cuvântul cheie extins pentru a defini o nouă clasă care moștenește proprietăți de la o clasă veche.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Elemente de SQL",false, "1. Cuvinte cheie: LIKE\n" +
                "\n" +
                "De exemplu, să presupunem că trebuie să identificăm numele familiilor care trăiesc în zona Boston care au drept nume de familie „Luis”. Următoarea interogare SQL va aduce rezultatele relevante pentru această declarație de problemă:\n" +
                "\n" +
                "SELECTAȚI * DIN [BOSTON] UNDE NUME CA „%LUIS”\n" +
                "\n" +
                "În mod similar, diferite cuvinte cheie efectuează diverse operații asupra bazelor de date. Următoarele sunt câteva exemple de astfel de cuvinte cheie cu rolurile lor funcționale:\n" +
                "\n" +
                "    CREA:\n" +
                "    INTRODUCE:\n" +
                "    SELECTAȚI:\n" +
                "    DIN:\n" +
                "    UNDE:\n" +
                "    ACTUALIZAȚI:\n" +
                "    ȘTERGE:\n" +
                "\n" +
                "2. Clauze:\n" +
                "\n" +
                "„SELECT Vârsta, E-mail, Adresă”\n" +
                "\n" +
                "3. Expresii: boolean, numeric, data\n" +
                "\n" +
                "Să luăm în considerare un exemplu de expresie booleană care preia date prin potrivirea unor valori individuale. Dacă doriți să identificați angajații ale căror salarii sunt egale cu 5.000, puteți utiliza următoarea interogare SQL: \n" +
                "\n" +
                "SELECTAȚI * DIN ANGAJAȚII UNDE SALARIU = 5000;\n" +
                "\n" +
                "4. Predicate:\n" +
                "\n" +
                "De exemplu, luați în considerare următoarea instrucțiune SQL:\n" +
                "\n" +
                "SELECTAȚI * FROM CLIENTS WHERE Produs = „Televizor”\n" +
                "\n" +
                "Aici, „Produs = Televiziune” este predicatul instrucțiunii SQL.\n" +
                "\n" +
                "5. Întrebări:\n" +
                "\n" +
                "SELECT First_Name, Customer_No FROM Customers WHERE Last_Name='Lobo';");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Quiz",false, "1");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,2,"Recapitulare",true, "");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Ce este Web-ul?",false, "Web-ul este un subset al internetului.\n" +
                "\n" +
                "Ca orice altă rețea de calculatoare, Web-ul este alcătuit din două componente principale: clientul browserului web și serverul web.\n" +
                "\n" +
                "Clientul solicită datele și serverul partajează sau servește datele acestuia. Pentru a realiza acest lucru, cele două părți trebuie să încheie un acord. Acel acord se numește Interfață de programare a aplicațiilor sau, pe scurt, API.\n" +
                "\n" +
                "Dar aceste date trebuie aranjate și formatate într-o formă care să fie înțeleasă de utilizatorii finali care au o gamă largă de experiențe și abilități tehnice.\n" +
                "\n" +
                "Aici intră în joc HTML, CSS, JavaScript și întregul concept de dezvoltare web.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Ce este HTML?",false, "Așadar, vă puteți gândi la HTML ca limbajul folosit pentru a crea instrucțiuni detaliate privind stilul, tipul, formatul, structura și alcătuirea unei pagini web înainte de a fi tipărită (vă arăta).\n" +
                "\n" +
                "Dar, în contextul dezvoltării web, putem înlocui termenul „tipărit” cu „redat” ca termen mai precis.\n" +
                "\n" +
                "HTML vă ajută să vă structurați pagina în elemente precum paragrafe, secțiuni, titluri, bare de navigare și așa mai departe.  \n" +
                "\n" +
                "Pentru a ilustra cum arată o pagină, să creăm un document HTML de bază:\n" +
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
                "Acesta este modul în care puteți formata și structura un document doar cu HTML. După cum puteți vedea, acest marcaj conține câteva elemente web, cum ar fi:\n" +
                "\n" +
                "    Titlu de nivel 1h1\n" +
                "    Titlu de nivel 2h2\n" +
                "    Titlul de nivel 3h3\n" +
                "    Un paragraf  p\n" +
                "    O listă neordonată cu puncte  ul li\n" +
                "    Un buton de intrareinput\n" +
                "    Și tot corpul paginiibody\n" +
                "\n" +
                "Iată ce redă markupul de mai sus pe un browser web:\n" +
                "HTML\n" +
                "localhost:3000/index.html\n" +
                "\n" +
                "De asemenea, puteți adăuga atribute acestor elemente pe care le puteți utiliza pentru a identifica elementele și a le accesa din alte locuri din site.\n" +
                "\n" +
                "În exemplul nostru, setăm idatributele tuturor celor trei spanelemente. Acest lucru ne va ajuta să le accesăm din JavaScript, așa cum veți vedea mai târziu.\n" +
                "\n" +
                "Gândiți-vă la acest atribut la fel ca numele de utilizator al rețelelor sociale. Cu acest nume, alții te pot găsi pe rețelele sociale. Și cineva se poate referi la tine sau te poate menționa cu acest nume (poți fi etichetat într-o postare și așa mai departe).\n" +
                "\n" +
                "Această pagină este totuși foarte simplă și neatrăgătoare. Dacă construiți altceva decât un demo, va trebui să adăugați un stil de bază pentru a-l face mai prezentabil. Și putem face exact asta cu CSS.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Ce este CSS?",false, "În timp ce HTML este un limbaj de marcare folosit pentru a formata/structura o pagină web, CSS este un limbaj de design pe care îl utilizați pentru a face pagina dvs. web să arate frumos și prezentabil.\n" +
                "\n" +
                "CSS înseamnă Cascading Style Sheets și îl folosiți pentru a îmbunătăți aspectul unei pagini web. Adăugând stiluri CSS bine gândite, vă faceți pagina mai atractivă și mai plăcută de vizualizat și utilizat de utilizatorul final.\n" +
                "\n" +
                "Imaginează-ți dacă ființele umane ar fi făcute doar pentru a avea schelete și oase goale - cum ar arăta asta? Nu e frumos dacă mă întrebi pe mine. Deci CSS este ca pielea, părul și aspectul fizic general.\n" +
                "\n" +
                "De asemenea, puteți utiliza CSS pentru a dispune elemente, poziționându-le în zonele specificate ale paginii dvs.\n" +
                "\n" +
                "Pentru a accesa aceste elemente, trebuie să le „selectați”. Puteți selecta unul sau mai multe elemente web și să specificați cum doriți să arate sau să fie poziționate.\n" +
                "\n" +
                "Regulile care guvernează acest proces se numesc selectoare CSS .\n" +
                "\n" +
                "Cu CSS puteți seta culoarea și fundalul elementelor dvs., precum și fontul, marginile, spațierea, umplutura și multe altele.\n" +
                "\n" +
                "Dacă vă amintiți pagina noastră HTML de exemplu, aveam elemente care se explicau de la sine. De exemplu, am afirmat că voi schimba culoarea titlului nivelului unu h1în roșu.\n" +
                "\n" +
                "Pentru a ilustra modul în care funcționează CSS, voi împărtăși codul care setează culoarea de fundal a celor trei niveluri de antete la roșu, albastru și, respectiv, verde:\n" +
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
                "Stilul de mai sus, atunci când este aplicat, va schimba aspectul paginii noastre web în acesta:\n" +
                "CSS\n" +
                "\n" +
                "Cool, nu?\n" +
                "\n" +
                "Accesăm fiecare dintre elementele la care dorim să lucrăm „selectându-le”. Selectează toate titlurile de h1nivelul 1 din pagină, h2selectează elementele de nivelul 2 și așa mai departe. Puteți selecta orice element HTML dorit și să specificați cum doriți să arate sau să fie poziționat.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Ce este JavaScript?",false, "Acum, dacă HTML este limbajul de marcare și CSS este limbajul de proiectare , atunci JavaScript este limbajul de programare.\n" +
                "\n" +
                "Dacă nu știi ce este programarea, gândește-te la anumite acțiuni pe care le faci în viața de zi cu zi:\n" +
                "\n" +
                "Când simți pericolul, fugi. Când ți-e foame, mănânci. Când ești obosit, dormi. Când ți-e frig, cauți căldură. Când traversați un drum aglomerat, calculați distanța dintre vehicule distanță de dvs.\n" +
                "\n" +
                "Creierul tău a fost programat să reacționeze într-un anumit mod sau să facă anumite lucruri ori de câte ori se întâmplă ceva. În același mod, îți poți programa pagina web sau elementele individuale să reacționeze într-un anumit fel și să facă ceva atunci când se întâmplă altceva (un eveniment).\n" +
                "\n" +
                "Puteți programa acțiuni, condiții, calcule, solicitări de rețea, sarcini concurente și multe alte tipuri de instrucțiuni.\n" +
                "\n" +
                "Puteți accesa orice elemente prin API-ul Document Object Model (DOM) și le puteți modifica oricum doriți.\n" +
                "\n" +
                "DOM-ul este o reprezentare arborescentă a paginii web care se încarcă în browser.\n" +
                "DOM-\n" +
                "Fiecare element de pe pagina web este reprezentat pe DOM\n" +
                "\n" +
                "Datorită DOM, putem folosi metode precum getElementById()accesarea elementelor din pagina noastră web.\n" +
                "\n" +
                "JavaScript vă permite să vă faceți pagina web „să gândească și să acționeze” , ceea ce înseamnă programare.\n" +
                "\n" +
                "Dacă vă amintiți din pagina noastră HTML de exemplu, am menționat că urma să însumez cele două numere afișate pe pagină și apoi să afișez rezultatul în locul textului substituent. Calculul rulează odată ce butonul este apăsat.\n" +
                "CSS-1\n" +
                "Făcând clic pe butonul „Obțineți suma” va afișa suma 2 și 7\n" +
                "\n" +
                "Acest cod ilustrează cum puteți face calcule cu JavaScript:\n" +
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
                "Vă amintiți ce v-am spus despre atributele HTML și despre utilizările lor? Acest cod afișează tocmai asta.\n" +
                "\n" +
                "Este displaySumo funcție care preia ambele elemente de pe pagina web, le convertește în numere (cu metoda Number), le însumează și le transmite ca valori interioare unui alt element.\n" +
                "\n" +
                "Motivul pentru care am putut accesa aceste elemente în JavaScript a fost pentru că le-am setat atribute unice, pentru a ne ajuta să le identificăm.\n" +
                "\n" +
                "Deci, datorită acestui lucru:\n" +
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
                "În cele din urmă, la apăsarea butonului, veți vedea suma celor două numere pe pagina nou actualizată:\n" +
                "JAVASCRIPT\n" +
                "2 plus 7 este egal cu 9\n");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Cum să puneți împreună HTML, CSS și JavaScript",false, "Împreună, folosim aceste trei limbi pentru a formata, proiecta și programa pagini web.\n" +
                "\n" +
                "Și atunci când conectați unele pagini web cu hyperlinkuri, împreună cu toate activele lor, cum ar fi imagini, videoclipuri și așa mai departe, care se află pe computerul server, acesta este redat într-un site web .\n" +
                "\n" +
                "Această redare are loc de obicei pe front-end, unde utilizatorii pot vedea ceea ce este afișat și pot interacționa cu acesta.\n" +
                "\n" +
                "Pe de altă parte, datele, în special informațiile sensibile precum parolele, sunt stocate și furnizate din partea de back-end a site-ului web. Aceasta este partea unui site web care există doar pe computerul server și nu este afișată pe browserul front-end. Acolo, utilizatorul nu poate vedea sau accesa cu ușurință acele informații.");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Quiz",false, "1");
        myDb.insertDataTheory(myTheory);
        myTheory = new Theory(1,3,"Recapitulare",true, "");
        myDb.insertDataTheory(myTheory);
    }
    public void insertCourses(){
        myCourse = new Course(1,
                "Conceptele Java de programare orientată pe obiecte, POO formează coloana vertebrală a programării Java.", false);
        /*
        if (myDb.insertDataCourse(myCourse)) {
            Toast.makeText(getBaseContext(), "Courses Register!", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(getBaseContext(), "1111111!", Toast.LENGTH_SHORT).show();
         */
        myDb.insertDataCourse(myCourse);
        myCourse = new Course(2,"SQL este un limbaj standardizat folosit pentru a interacționa cu bazele de date relaționale.", false);
        myDb.insertDataCourse(myCourse);
        myCourse = new Course(3,"Aflați elementele de bază ale dezvoltării web – HTML, CSS și JavaScript explicate pentru începători", false);
        myDb.insertDataCourse(myCourse);
    }
}