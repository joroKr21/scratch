import org.scalacheck.Properties
import org.scalacheck.Prop

import scala.io.{Codec, Source}

object LZ77Spec extends Properties("LZ77") {

  val quote = """
    |I don't know how many of you have ever met Dijkstra, but you probably know that
    |arrogance in computer science is measured in nano-Dijkstras.
    |""".stripMargin.trim

  val wikipedia = """
    |Cockroach Labs is a computer software company that develops database management
    |systems for businesses. It is best known for CockroachDB, which has been
    |compared to Google's Spanner database. CockroachDB is an open-sourced software
    |project that is designed to store copies of data in multiple locations in order
    |to deliver requested data when needed. It is described as a scalable,
    |consistently-replicated, transactional datastore.
    |
    |Cockroach Labs was founded in 2015 by ex-Google employees Spencer Kimball, Peter
    |Mattis, and Ben Darnell. Prior to Cockroach Labs, Kimball and Mattis were key
    |members of the Google File System team while Darnell was a key member of the
    |Google Reader team. While at Google, all three had previously used BigTable and
    |were acquainted with its successor, Spanner. After leaving Google, they wanted
    |to design and build something similar for companies outside of Google. By June
    |2015, the company had nine engineers working on its CockroachDB software.
    |
    |Spencer Kimball wrote the first iteration of the design in January 2014 and
    |began the open-source project on GitHub in February 2014, allowing outside
    |access and contributions. It attracted a community of experienced contributors,
    |with the co-founders also actively developing the project with conferences,
    |networking, and meet-ups. Its collaborations on GitHub earned it the honor of
    |Open Source Rookie of the Year, a title awarded by Black Duck Software to the
    |top new open-source projects.
    |
    |In June 2015, the company closed $6.25 million in funding from Benchmark,
    |Sequoia, Google Ventures, and FirstMark Capital. As a result of the funding,
    |Benchmark's general partner Peter Fenton was named to the company's board of
    |directors. Additional investors in the funding round were disclosed as
    |Hortonworks chief executive Rob Bearden, CoreOS CEO Alex Polvi, and Cloudera
    |co-founder Jeff Hammerbacher.
    |
    |Cockroach Labs' main software is CockroachDB, an open-source database built
    |using a Google whitepaper on Spanner. The database is scalable in that a single
    |instance can be run from a laptop while building an app, then scaled to
    |thousands of commodity servers as a business grows.
    |
    |CockroachDB is designed to run in the cloud and be resilient to failures.
    |CockroachDB is open-source software that is designed to store copies of data in
    |multiple locations in order to deliver requested data when needed. The result is
    |a database that is described as "almost impossible" to take down. Even if
    |multiple servers or an entire datacenter were to go offline, CockroachDB would
    |aim to keep services online.
    |""".stripMargin.trim

  def test(text: String): Boolean =
    LZ77.Naive.decode(LZ77.Naive.encode(text)) == text

  def testSST(name: String): Boolean =
    test(Source.fromResource(s"$name.sst")(Codec.ISO8859).mkString)

  property("naive") = Prop.forAll(test _)
  property("quote") = Prop(test(quote))
  property("wikipedia") = Prop(test(wikipedia))
//  property("500kb") = Prop(testSST("500kb"))
}
