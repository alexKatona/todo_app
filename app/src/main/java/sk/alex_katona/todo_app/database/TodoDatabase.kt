package sk.alex_katona.todo_app.database

import androidx.room.*

@Database(version = 1, entities = [TodoEntity::class])
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDAO
}

@Entity(tableName = "todo_entity")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "todo_title") val title: String
)

@Dao
interface TodoDAO {
    @Query("SELECT * FROM todo_entity")
    suspend fun getAll(): List<TodoEntity>

    @Query("SELECT * FROM todo_entity WHERE id =:id ")
    suspend fun getById(id: Int): TodoEntity?

    @Insert(entity = TodoEntity::class, onConflict = OnConflictStrategy.ABORT)
    suspend fun addAll(vararg todoEntity: TodoEntity)

    @Delete(entity = TodoEntity::class)
    suspend fun delete(todoEntity: TodoEntity)
}