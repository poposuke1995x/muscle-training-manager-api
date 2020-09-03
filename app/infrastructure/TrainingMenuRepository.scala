package infrastructure

import domain._
import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.jdbc.JdbcProfile

import scala.concurrent.{ExecutionContext, Future}

class TrainingMenuRepository @Inject()(protected val dbConfigProvider: DatabaseConfigProvider)(implicit executionContext: ExecutionContext)
  extends HasDatabaseConfigProvider[JdbcProfile] with TrainingMenuRepositoryInterface {

  import profile.api._

  private val TrainingMenuObj = TableQuery[TrainingMenuTable]

  def index(): Future[Seq[TrainingMenu]] = db.run(TrainingMenuObj.result)

  def findById(id: Int): Future[TrainingMenu] = db.run(TrainingMenuObj.filter(_.id === id).result.head)

  def insert(trainingMenu: TrainingMenu): Future[Int] = db.run(TrainingMenuObj += trainingMenu)

  def update(trainingMenu: TrainingMenu): Future[Int] = db.run(TrainingMenuObj.filter(_.id === trainingMenu.id).update(trainingMenu))

  def delete(id: Int): Future[Int] = db.run(TrainingMenuObj.filter(_.id === id).delete)

  private class TrainingMenuTable(tag: Tag) extends Table[TrainingMenu](tag, "training_menu") {

    def id = column[Option[Int]]("id", O.PrimaryKey, O.AutoInc)

    def name = column[String]("name")

    def categoryId = column[Int]("category_id")

    def description = column[Option[String]]("description")

    def importedCount = column[Int]("imported_count")

    def userUid = column[String]("user_uid")

    def shareFlag = column[Boolean]("share_flag")

    def * = (
      id,
      name,
      categoryId,
      description,
      importedCount,
      userUid,
      shareFlag
    ) <> (TrainingMenu.tupled, TrainingMenu.unapply)

  }

}