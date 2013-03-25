import sbt._
import Keys._

object TestBuild extends Build
{
    val gccOptions = TaskKey[Seq[String]]("gcc-options", "Options to pass to gcc")
    val archiveOptions = TaskKey[Seq[String]]("archive-options", "Options to pass to ar")
    val projectRootDir = TaskKey[File]("project-root", "Project root directory")
    val sourceFiles = TaskKey[Seq[File]]("", "")
    val objectFiles = TaskKey[Seq[File]]("", "")
    val archiveCreation = TaskKey[File]("object", "Object files input to archive")
    
    projectRootDir := new File("/home/alex/Devel/AW/cpp11tests/libraries")
    
    sourceFiles <<= (projectRootDir) map
    { rootDir =>
        
        //val all = IO.listFiles( rootDir / "source", NameFilter.fnToNameFilter( _.endsWith(".cpp") ) )
        val all = rootDir ** "*.cpp"
        
        all.get
    }
    
    objectFiles <<= (sourceFiles, gccOptions) map
    { case (sf, gcco) =>
        // TODO: Do some compilation here
        
        sf.map( f => new File( f.base + ".obj" ) )
    }
    
    
    archiveCreation <<= (objectFiles, archiveOptions) map
    { case (of, ao) =>
    
        new File( "some_old_archive" )
    }
        
    lazy val foo = Project( id="foo", base=file(".") )
}


