package ar.edu.unahur.obj2.caralibro

class Usuario {
  val publicaciones = mutableListOf<Publicacion>()
  var listaDeAmigos = mutableListOf<Usuario>()
  var listaDePermitidos = mutableListOf<Usuario>()
  var listaDeExcluidos = mutableListOf<Usuario>()


  fun agregarPublicacion(publicacion: Publicacion, permiso:String) {
    publicaciones.add(publicacion)
    publicacion.asignarPermiso(permiso)
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


  fun puedeVerPublicacion(publicacion: Publicacion, amigo: Usuario){
    publicacion.permisos(this,amigo)
  }

//serie de verificaciones , si la puede ver etc

}






