//import scala.io.Source
//
//// Définition de la classe pour représenter les objets
//data class Person(name: String, age: Int, email: String)
//
//object FileToClass {
//  def main(args: Array[String]): Unit = {
//    // Chemin du fichier à lire
//    val filePath = "path/to/your/file.txt"
//
//    // Lecture du fichier ligne par ligne
//    val lines = Source.fromFile(filePath).getLines()
//
//    // Transformation des lignes en objets Person
//    val persons = lines.map { line =>
//      val parts = line.split(",").map(_.trim) // Diviser la ligne par les virgules et supprimer les espaces
//      Person(parts(0), parts(1).toInt, parts(2)) // Créer un objet Person
//    }.toList
//
//    // Affichage des objets Person
//    persons.foreach(println)
//  }
//}