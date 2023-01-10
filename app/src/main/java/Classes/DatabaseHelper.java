package Classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "proiect45.db";

    public static final String TABLE_NAME_USER = "user";
    public static final String COL_ID = "idUser";
    public static final String COL_NAME = "Name";
    public static final String COL_EMAIL = "Email";
    public static final String COL_PASSWORD = "Password";
    public static final String COL_TYPE = "Type";

    public static final String TABLE_NAME_COURSE = "course";
    public static final String COL_ID_COURSE = "idCours";
    public static final String COL_DESCRIPTION = "Description";
    public static final String COL_CHECK = "Check_View";

    public static final String TABLE_NAME_QUESTION = "question";
    public static final String COL_ID_QUESTION = "idQuestion";
    public static final String COL_COURES = "id_Course";
    public static final String COL_QUESTION = "Question";
    public static final String COL_ANSWER = "Answer";
    public static final String COL_A = "First_Col";
    public static final String COL_B = "Second_Col";
    public static final String COL_C = "Third_Col";
    public static final String COL_D = "Fourth_Col";

    public static final String TABLE_NAME_THEORY = "theory";
    public static final String COL_ID_THEORY = "idtheory";
    public static final String COL_COURE = "id_Cours";
    public static final String COL_READ = "Read";
    public static final String COL_TITLE = "Title";
    public static final String COL_THEORY = "Theory";

    public static final String TABLE_NAME_READ = "read";
    public static final String COL_ID_READ = "idRead";
    public static final String COL_COURSE_ID = "idCourse";
    public static final String COL_THEORY_ID = "idTheor_1";
    public static final String COL_USER_ID = "idUser";
    public static final String COL_CHECK_ID = "Check_2";

    public static final String TABLE_NAME_QUIZ = "quiz";
    public static final String COL_ID_QUIZ = "idQuiz";
    public static final String COL_COURSE = "idCourse";
    public static final String COL_USER = "idUser";
    public static final String COL_SCORE_id = "score_1";

    public static final String TABLE_NAME_ANSWORE = "ansore";
    public static final String COL_ID_ANSWORE  = "idAnsore";
    public static final String COL_COURSE_id = "idCourse";
    public static final String COL_USER_id = "idUser";
    public static final String COL_QUESTION_id = "idQuestion";
    public static final String COL_ANSWORE = "Answore";
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME_USER
                + " (" + COL_ID + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, "
                + COL_NAME + " TEXT  NOT NULL, "
                + COL_EMAIL + " TEXT UNIQUE NOT NULL, "
                + COL_PASSWORD + " TEXT  NOT NULL, "
                + COL_TYPE + " TEXT  NOT NULL);");
        db.execSQL("CREATE TABLE " + TABLE_NAME_COURSE
                + " (" + COL_ID_COURSE + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, "
                + COL_DESCRIPTION + " TEXT  NOT NULL, "
                + COL_CHECK + " TEXT  NOT NULL);");
        db.execSQL("CREATE TABLE " + TABLE_NAME_QUESTION
                + " (" + COL_ID_QUESTION + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, "
                + COL_COURES + " INTEGER  NOT NULL ,"
                + COL_QUESTION + " TEXT  NOT NULL, "
                + COL_ANSWER + " INTEGER  NOT NULL ,"
                + COL_A + " TEXT  NOT NULL, "
                + COL_B + " TEXT  NOT NULL, "
                + COL_C + " TEXT  NOT NULL, "
                + COL_D + " TEXT  NOT NULL);");
        db.execSQL("CREATE TABLE " + TABLE_NAME_THEORY
                + " (" + COL_ID_THEORY + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, "
                + COL_COURE + " INTEGER  NOT NULL ,"
                + COL_TITLE + " TEXT  NOT NULL, "
                + COL_READ + " BOOLEAN  NOT NULL, "
                + COL_THEORY + " TEXT  NOT NULL);");
        db.execSQL("CREATE TABLE " + TABLE_NAME_READ
                + " (" + COL_ID_READ + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, "
                + COL_COURSE_ID + " TEXT  NOT NULL ,"
                + COL_USER_ID + " TEXT  NOT NULL, "
                + COL_THEORY_ID + " TEXT NOT NULL, "
                + COL_CHECK_ID + " BOOLEAN  NOT NULL);");
        db.execSQL("CREATE TABLE " + TABLE_NAME_QUIZ
                + " (" + COL_ID_QUIZ + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, "
                + COL_COURSE + " TEXT  NOT NULL ,"
                + COL_USER + " TEXT  NOT NULL, "
                + COL_SCORE_id + "  INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE " + TABLE_NAME_ANSWORE
                + " (" + COL_ID_ANSWORE + " INTEGER UNIQUE PRIMARY KEY AUTOINCREMENT, "
                + COL_COURSE_id + " TEXT  NOT NULL ,"
                + COL_USER_id + " TEXT  NOT NULL ,"
                + COL_QUESTION_id + " TEXT  NOT NULL, "
                + COL_ANSWORE + " TEXT NOT NULL);");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_USER);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME_COURSE);
        onCreate(db);
    }
    public boolean insertDataUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, user.getName());
        values.put(COL_EMAIL, user.getEmail());
        values.put(COL_PASSWORD, user.getPassword());
        values.put(COL_TYPE, user.getType());
        long result = db.insert(TABLE_NAME_USER, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertDataRead(Read read) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_COURSE_ID, read.getCourse_id());
        values.put(COL_USER_ID, read.getUser_id());
        values.put(COL_THEORY_ID, read.getIdTheor_1());
        values.put(COL_CHECK_ID, read.getCheck());
        long result = db.insert(TABLE_NAME_READ, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertDataQuiz(Quiz quiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_COURSE, quiz.getIdCourse());
        values.put(COL_USER, quiz.getIdUser());
        values.put(COL_SCORE_id, quiz.getScore());
        long result = db.insert(TABLE_NAME_QUIZ, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertDataAnsowere(Answer quiz) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_COURSE_id, quiz.getIdCourse());
        values.put(COL_USER_id, quiz.getIdUser());
        values.put(COL_QUESTION_id, quiz.getIdQuestion());
        values.put(COL_ANSWORE, quiz.getAnswer());
        long result = db.insert(TABLE_NAME_ANSWORE, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertDataCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_DESCRIPTION, course.getDescription());
        values.put(COL_CHECK, course.getCheck());
        long result = db.insert(TABLE_NAME_COURSE, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertDataQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_COURES, question.getId_course());
        values.put(COL_QUESTION, question.getQuestion());
        values.put(COL_ANSWER, question.getAnswer());
        values.put(COL_A, question.getFirst_col());
        values.put(COL_B,question.getSecond_col());
        values.put(COL_C, question.getThird_col());
        values.put(COL_D, question.getFourth_col());
        long result = db.insert(TABLE_NAME_QUESTION, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertDataTheory(Theory theory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_COURE, theory.getId_course());
        values.put(COL_TITLE, theory.getTitle());
        values.put(COL_READ,theory.getRead());
        values.put(COL_THEORY, theory.getTheory());
        long result = db.insert(TABLE_NAME_THEORY, null, values);
        if (result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllDataUser() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_USER;
        Cursor result = db.rawQuery(query,null);
        return result;
    }
    public Cursor getAllDataAnswore() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_ANSWORE;
        Cursor result = db.rawQuery(query,null);
        return result;
    }
    public Cursor getAllDataCourse(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_COURSE;
        Cursor result = db.rawQuery(query,null);
        return result;
    }
    public Cursor getAllDataQuestions(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_QUESTION;
        Cursor result = db.rawQuery(query,null);
        return result;
    }
    public Cursor getAllDataQuiz(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_QUIZ;
        Cursor result = db.rawQuery(query,null);
        return result;
    }
    public Cursor getAllDataTheory(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_THEORY;
        Cursor result = db.rawQuery(query,null);
        return result;
    }
    public Cursor getAllDataRead(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "Select * from " + TABLE_NAME_READ;
        Cursor result = db.rawQuery(query,null);
        return result;
    }
    public Cursor getUserLogin(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_USER, null,"email = ? AND password = ? AND type = ?",
                new String[] {user.getEmail(), user.getPassword(), user.getType()},
                null,null,null);
    }
    public Cursor getUserType(String type) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_USER, null,"type = ?",
                new String[] {type}, null,null,null);
    }
    public Cursor getCursorById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_THEORY, null,"id_Cours = ?",
                new String[] {id}, null,null,null);
    }
    public Cursor getReadByIdUser(String idUser, String idCourse, String idTheor_1) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_READ, new String[]{"DISTINCT idTheor_1"},"idUser = ? AND idCourse = ? AND idTheor_1 = ?",
                new String[] {idUser, idCourse, idTheor_1}, null,null,null);
    }
    public Cursor getAvarageQuiz(String str1) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_QUIZ, new String[]{"idQuiz, idCourse, idUser, AVG(score_1) as avg, MAX(score_1) as max"},"idQuiz AND idCourse = ?",
                new String[]{str1}, "idUser",null,null);
    }
    public Cursor getAvarageAnswer(String str1) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_ANSWORE, new String[]{"idAnsore, idCourse, idQuestion, idCourse, COUNT(Answore) as b"},"idCourse = ?",
                new String[]{str1}, "idQuestion",null,"idQuestion ASC");
    }
    public Cursor getAvarageAnswerTotal1(String str1) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_ANSWORE, new String[]{"idAnsore, idCourse, idQuestion, COUNT(Answore) as a"},"idQuestion AND idCourse = ? AND Answore = ?",
                new String[]{str1,"correct"}, "idQuestion",null,"idQuestion ASC");
    }
    public Cursor getAvarageAnswerTotal2(String str1) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_ANSWORE, new String[]{"idAnsore, idCourse, idQuestion, COUNT(Answore) as a"},"idQuestion AND idCourse = ? AND Answore = ?",
                new String[]{str1,"incorrect",}, "idQuestion",null,"idQuestion ASC");
    }
    public Cursor getAvarageAnswerCount(String str1) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_ANSWORE, new String[]{"idQuestion, count(idQuestion)"},"idCourse = ?",
                new String[]{str1}, "idQuestion",null,"idQuestion ASC ");
    }
    public Cursor getTheoryById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_THEORY, null,"idtheory = ?",
                new String[] {id}, null,null,null);
    }
    public Cursor getQuestionById(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_QUESTION, null,"id_Course = ?",
                new String[] {id}, null,null,null);
    }
    public Cursor getQuestion(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_QUESTION, null,"idQuestion = ?",
                new String[] {id}, null,null,null);
    }
    public Cursor getQuestionByIdAndIncorrect(String id, String user,String answer) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_ANSWORE, new String[]{"DISTINCT idQuestion "},"idCourse = ? AND idUser = ? AND Answore = ?",
                new String[] {id,user,answer}, null,null,"idQuestion DESC" );
    }
    public Cursor getQuestionByIdQuestion(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_QUESTION, null,"idQuestion = ?",
                new String[] {id}, null,null,null);
    }
    public void deleteUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_USER, COL_ID +"= ?",
                new String[]{ String.valueOf(user.getId()) });
        db.close();
    }
}
