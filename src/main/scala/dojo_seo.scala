import net.ruippeixotog.scalascraper.browser.JsoupBrowser
import net.ruippeixotog.scalascraper.dsl.DSL.Extract._
import net.ruippeixotog.scalascraper.dsl.DSL._

object dojo_char extends App {
  def contentByUrl(url: String) = {
    JsoupBrowser().get(url) >> texts(".content") mkString
  }

  def flipLetter(l: String) =  l match {
    case "á" | "à" | "ã" => "a"
    case "é" | "ê" => "e"
    case "ó" | "ô" | "õ" => "o"
    case "ú" | "ü" => "u"
    case "í" => "i"
    case "ç" => "c"
    case ext: String => ext
  }

  def normalize(s: String) = {
    (s.toLowerCase().split("") map (flipLetter(_)) mkString).replaceAll("(\\W|\\d| |_)+", " ")
  }

  val link = "http://diversao.terra.com.br/tv/sala-de-tv/blog/2016/07/02/gloria-maria-fica-%E2%80%98tonta%E2%80%99-ao-fumar-maconha-e-a-internet-pira/"
  val siteText = contentByUrl(link)
  val text = normalize(siteText)

  print(text)
}

