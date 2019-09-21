
package com.displayfort.feedback.data.local.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.displayfort.feedback.data.local.db.dao.OptionDao;
import com.displayfort.feedback.data.local.db.dao.QuestionDao;
import com.displayfort.feedback.data.local.db.dao.UserDao;
import com.displayfort.feedback.data.model.db.Option;
import com.displayfort.feedback.data.model.db.Question;
import com.displayfort.feedback.data.model.db.User;

/**
 * Created by Yogesh  on 07/07/17.
 */

@Database(entities = {User.class, Question.class, Option.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OptionDao optionDao();

    public abstract QuestionDao questionDao();

    public abstract UserDao userDao();
}
