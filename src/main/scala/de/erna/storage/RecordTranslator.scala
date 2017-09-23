package de.erna.storage

trait RecordTranslator[ Ext, Int ] {
  def toInternal( record: Ext ): Int

  def toExternal( record: Int ): Ext
}
