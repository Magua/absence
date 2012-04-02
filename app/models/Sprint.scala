package models

import java.util.{Date}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

import anorm._
import anorm.SqlParser._

case class Sprint(id: Pk[Long], sprint_nr: Long, start_date: Option[Date], end_date: Option[Date]) 

object Sprint{
  
  // -- Parsers
  
  /**
   * Parse a Sprint from a ResultSet
   */
  val simple = {
    get[Pk[Long]]("sprint.id") ~
    get[Long]("sprint.sprint_nr") ~
    get[Option[Date]]("sprint.start_date") ~
    get[Option[Date]]("sprint.end_date") map {
      case id~sprint_nr~start_date~end_date => Sprint(
        id, sprint_nr, start_date, end_date
      )
    }
  }
  
  
  // -- Queries  
  
  /**
   * Create a Sprint.
   */
  def create(sprint: Sprint): Sprint = {
    DB.withConnection { implicit connection =>
      
      // Get the task id
      val id: Long = sprint.id.getOrElse {
        SQL("select next value for sprint_seq").as(scalar[Long].single)
      }
      
      SQL(
        """
          insert into sprint values (
            {id}, {sprint_nr}, {start_date}, {end_date}
          )
        """
      ).on(
        'id -> id,
        'sprint_nr -> sprint.sprint_nr,
        'start_date -> sprint.start_date,
        'end_date -> sprint.end_date
      ).executeUpdate()
      
      sprint.copy(id = Id(id))
      
    }  
  }
  
  /**
   * Delete a Sprint
   */
  def delete(id: Long) {
    DB.withConnection { implicit connection =>
      SQL("delete from sprint where id = {id}").on(
        'id -> id
      ).executeUpdate()
    }
  } 
  
  /**
   * Retrieve a Sprint from the id.
   */
  def findById(id: Long): Option[Sprint] = {
    DB.withConnection { implicit connection =>
      SQL("select * from sprint where id = {id}").on(
        'id -> id
      ).as(Sprint.simple.singleOpt)
    }
  }
  
  /**
   * Retrieve all Sprints.
   */
  def findAll: Seq[Sprint] = {
    DB.withConnection { implicit connection =>
      SQL("select * from sprint").as(Sprint.simple *)
    }
  }  
  
}