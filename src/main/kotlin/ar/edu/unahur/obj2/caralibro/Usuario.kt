package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  var listaDeAmigos = mutableListOf<Usuario>()


  fun agregarPublicacion(publicacion: Publicacion) {
    publicaciones.add(publicacion)
  }

  fun espacioDePublicaciones() =
    publicaciones.sumBy { it.espacioQueOcupa() }

  fun agregarAmigo(amigo: Usuario) {
    listaDeAmigos.add(amigo)
  }

  fun darMegusta(publicacion:Publicacion) {
    publicacion.recibeMeGusta(this)
  }

  fun cantidadDeAmigos() =
    listaDeAmigos.size

  fun esMasAmistosoQue(usuario : Usuario ) =
    this.cantidadDeAmigos() > usuario.cantidadDeAmigos()

  fun cantidadTotalDeMeGusta()=
    publicaciones.sumBy{ it.devolverCantidadDeMeGustaDeLaPublicacion()}

  fun amigoMasPopular()=
    listaDeAmigos.maxByOrNull{ it.cantidadTotalDeMeGusta() }

  /*
  fun puedeVerPublicacion(publicacion: Publicacion){
    publicacion.permiso.permisoDeLaPublicacion(this)
  }
  */
//serie de verificaciones , si la puede ver etc

}






