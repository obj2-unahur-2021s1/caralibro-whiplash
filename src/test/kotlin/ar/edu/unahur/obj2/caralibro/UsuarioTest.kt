package ar.edu.unahur.obj2.caralibro

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class

UsuarioTest : DescribeSpec({
  describe("Caralibro") {
    val saludoCumpleanios = Texto("Felicidades Pepito, que los cumplas muy feliz")
    val fotoEnCuzco = Foto(768, 1024)
    val fotoEnEsquel = Foto(800,1000,)
    val videoEnElSur = Video(videoSd(150))
    val videoEnElNorte = Video(videoHd720p(100))
    val videoEnElOeste = Video(videoHd1080p(120))
    val fotoEnAconcagua = Foto(400,600)
    val pepe = Usuario()
    val jose = Usuario()
    val luis = Usuario()
    val juanito = Usuario()
    val oscarcito = Usuario()
    val pilar = Usuario()
    val juanita = Usuario()


    describe("Una publicación") {
      describe("de tipo foto") {
        it("ocupa ancho * alto * compresion bytes") {
          fotoEnCuzco.espacioQueOcupa().shouldBe(550503)
        }
        it("ocupa ancho * alto * compresion bytes de foto en esquel"){
          fotoEnEsquel.espacioQueOcupa().shouldBe(560000)
        }
        it("ocupa ancho * alto * compresion bytes de foto en aconcagua"){
          fotoEnAconcagua.espacioQueOcupa().shouldBe(168000)
        }
        it("cambiamos el valor de compresion a 0.2 y cambia el valor de las 3 anteriores"){
          FactorDeCompresion.cambiarFactor(0.2)
          fotoEnCuzco.espacioQueOcupa().shouldBe(157287)
          fotoEnEsquel.espacioQueOcupa().shouldBe(160000)
          fotoEnAconcagua.espacioQueOcupa().shouldBe(48000)
        }

      }

      describe("de tipo texto") {
        it("ocupa tantos bytes como su longitud") {
          saludoCumpleanios.espacioQueOcupa().shouldBe(45)
        }
      }

      describe("de tipo de video"){
        it("tipo de video sd ocupa tantos bytes como su duracion en segundos"){
          videoEnElSur.espacioQueOcupa().shouldBe(150)
        }
        it("tipo de video hd720p es el triple de su duracion en seg"){
          videoEnElNorte.espacioQueOcupa().shouldBe(300)
        }
        it("tipo de video hd1080p es el doble de 720p"){
          videoEnElOeste.espacioQueOcupa().shouldBe(720)
        }
        it("cambia la calidad de video de una publicacion"){
          //videoEnElNorte.setCalidad(videoSd)
        }
      }

    }

    describe("Un usuario") {

      it("puede calcular el espacio que ocupan sus publicaciones") {
        val juana = Usuario()
        juana.agregarPublicacion(fotoEnCuzco,"publico")
        juana.agregarPublicacion(fotoEnAconcagua,"publico")
        juana.agregarPublicacion(fotoEnEsquel,"publico")
        juana.agregarPublicacion(saludoCumpleanios,"publico")
        juana.espacioDePublicaciones().shouldBe(1278548)
      }

    describe("Probamos como los usuarios dan like"){

      pepe.agregarPublicacion(fotoEnAconcagua, "publico")
      jose.darMegusta(fotoEnAconcagua)
      luis.darMegusta(fotoEnAconcagua)

      it("dos usuarios dan me gusta a publicacion"){
        fotoEnAconcagua.cantidadDeMeGustaDeLaPublicacion.shouldBe(2)
        fotoEnAconcagua.usuariosQueDieronLikeALaPublicacion.shouldContainExactly(jose,luis)
      }

      it("usuario no puede dar like a una publicacion porque ya dio like"){
        shouldThrowAny { jose.darMegusta(fotoEnAconcagua) }
      }

    }

    describe("Probando funcion es mas amistoso que"){
      jose.agregarAmigo(juanito)
      jose.agregarAmigo(oscarcito)
      jose.agregarAmigo(pilar)
      pepe.agregarAmigo(jose)
      pepe.agregarAmigo(juanita)
      it("jose es mas amistoso que pepe"){
        jose.esMasAmistosoQue(pepe).shouldBeTrue()
      }

      it("agregamos amigos a pepe y ahora es mas amistoso que jose"){
        pepe.agregarAmigo(luis)
        pepe.agregarAmigo(pilar)
        pepe.esMasAmistosoQue(jose).shouldBeTrue()
      }
    }


    describe("Probando funcion amigo mas popular ") {
      it("con permisos en publico"){
        jose.agregarAmigo(juanito)
        jose.agregarAmigo(oscarcito)
        jose.agregarAmigo(pilar)

        juanito.agregarPublicacion(fotoEnAconcagua, "publico")
        oscarcito.agregarPublicacion(videoEnElSur, "publico")
        oscarcito.agregarPublicacion(videoEnElOeste, "publico")
        pilar.agregarPublicacion(fotoEnAconcagua, "publico")
        pilar.agregarPublicacion(fotoEnCuzco, "publico")
        pilar.agregarPublicacion(fotoEnEsquel, "publico")

        pilar.darMegusta(fotoEnAconcagua)
        pilar.darMegusta(videoEnElSur)
        juanito.darMegusta(fotoEnEsquel)

        jose.amigoMasPopular().shouldBe(pilar)
      }
    }

    describe(" requerimiento 4 , ver si un usuario puede ver cierta publicacion"){
      it("pilar/pepe pueden ver las publicaciones de juanito pero oscarcito no"){

        juanito.agregarPublicacion(fotoEnCuzco,"solo amigos")
        juanito.agregarPublicacion(videoEnElNorte,"solo amigos")
        juanito.agregarAmigo(pilar)
        juanito.agregarAmigo(pepe)
        pilar.puedeVerPublicacion(fotoEnCuzco,juanito).shouldBeTrue()
        oscarcito.puedeVerPublicacion(fotoEnCuzco,juanito).shouldBeFalse()
        pepe.puedeVerPublicacion(videoEnElNorte, juanito).shouldBeTrue()
        juanito.puedeVerPublicacion(videoEnElNorte,juanito).shouldBeTrue()
      }

      }

    }
  }
})
