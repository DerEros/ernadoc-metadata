package de.erna.api

import de.erna.storage.{MetaDataDAO, RecordTranslator}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping( Array( "/api" ) )
class Api @Autowired()( metaDataDAO: MetaDataDAO, recordTranslator: RecordTranslator[ String, Object ] ) {

  @PostMapping( consumes = Array( "application/json" ), produces = Array( "application/json" ) )
  def addRecord( @RequestBody record: String ): String = {
    val translatedRecord = recordTranslator.toInternal( record )
    metaDataDAO.store( translatedRecord )

    recordTranslator.toExternal( translatedRecord )
  }




}
