package de.erna.api

import com.mongodb.DBObject
import com.mongodb.util.JSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping( Array( "/api" ) )
class Api @Autowired()( val mongoTemplate: MongoTemplate ) {

  @GetMapping
  def index( ): String = {
    s"Hello World! ${mongoTemplate.getDb.getName}"
  }

  @PostMapping( Array( "/users" ) )
  def addUser( @RequestBody name: String ): String = {
    val record = "{\n  \"version\": \"1.0\",\n  \"documentType\": \"user\",\n  \"name\": \"" + name + "\"\n}"
    val parsedRecord = parseRecord( record )
    mongoTemplate.insert( parsedRecord, "users" )

    prepareForReturn( parsedRecord )
  }

  @PostMapping( consumes = Array( "application/json" ), produces = Array( "application/json" ) )
  def addRecord( @RequestBody record: String ): String = {
    val parsedRecord = parseRecord( record )
    val collection = findCollection( parsedRecord )
    mongoTemplate.insert( parsedRecord, collection )

    prepareForReturn( parsedRecord )
  }

  def findCollection( parsedRecord: DBObject ) = "users"

  def prepareForReturn( record: DBObject ): String = {
    renameIdField( record )
    JSON.serialize( record )
  }

  private def parseRecord( record: String ) = {
    JSON.parse( record ).asInstanceOf[ DBObject ]
  }

  private def renameIdField( record: DBObject ) = {
    record.put( "id", record.get( "_id" ).toString )
    record.removeField( "_id" )
  }

}
