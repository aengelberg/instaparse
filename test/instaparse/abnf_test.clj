(ns instaparse.abnf-test
  (:use clojure.test)
  (:use instaparse.core))

(deftest abnf-uri
  (let [uri-parser (binding [instaparse.abnf/*case-insensitive?* true]
                     (parser (slurp "test/instaparse/abnf_uri.txt") :input-format :abnf))]
    (are [x y] (= x y)
         (uri-parser "http://www.google.com")
         [:URI [:SCHEME [:ALPHA "h"] [:ALPHA "t"] [:ALPHA "t"] [:ALPHA "p"]] ":" [:HIER-PART "//" [:AUTHORITY [:HOST [:REG-NAME [:UNRESERVED [:ALPHA "w"]] [:UNRESERVED [:ALPHA "w"]] [:UNRESERVED [:ALPHA "w"]] [:UNRESERVED "."] [:UNRESERVED [:ALPHA "g"]] [:UNRESERVED [:ALPHA "o"]] [:UNRESERVED [:ALPHA "o"]] [:UNRESERVED [:ALPHA "g"]] [:UNRESERVED [:ALPHA "l"]] [:UNRESERVED [:ALPHA "e"]] [:UNRESERVED "."] [:UNRESERVED [:ALPHA "c"]] [:UNRESERVED [:ALPHA "o"]] [:UNRESERVED [:ALPHA "m"]]]]] [:PATH-ABEMPTY]]]
         
         (uri-parser "ftp://ftp.is.co.za/rfc/rfc1808.txt")
         [:URI [:SCHEME [:ALPHA "f"] [:ALPHA "t"] [:ALPHA "p"]] ":" [:HIER-PART "//" [:AUTHORITY [:HOST [:REG-NAME [:UNRESERVED [:ALPHA "f"]] [:UNRESERVED [:ALPHA "t"]] [:UNRESERVED [:ALPHA "p"]] [:UNRESERVED "."] [:UNRESERVED [:ALPHA "i"]] [:UNRESERVED [:ALPHA "s"]] [:UNRESERVED "."] [:UNRESERVED [:ALPHA "c"]] [:UNRESERVED [:ALPHA "o"]] [:UNRESERVED "."] [:UNRESERVED [:ALPHA "z"]] [:UNRESERVED [:ALPHA "a"]]]]] [:PATH-ABEMPTY "/" [:SEGMENT [:PCHAR [:UNRESERVED [:ALPHA "r"]]] [:PCHAR [:UNRESERVED [:ALPHA "f"]]] [:PCHAR [:UNRESERVED [:ALPHA "c"]]]] "/" [:SEGMENT [:PCHAR [:UNRESERVED [:ALPHA "r"]]] [:PCHAR [:UNRESERVED [:ALPHA "f"]]] [:PCHAR [:UNRESERVED [:ALPHA "c"]]] [:PCHAR [:UNRESERVED [:DIGIT "1"]]] [:PCHAR [:UNRESERVED [:DIGIT "8"]]] [:PCHAR [:UNRESERVED [:DIGIT "0"]]] [:PCHAR [:UNRESERVED [:DIGIT "8"]]] [:PCHAR [:UNRESERVED "."]] [:PCHAR [:UNRESERVED [:ALPHA "t"]]] [:PCHAR [:UNRESERVED [:ALPHA "x"]]] [:PCHAR [:UNRESERVED [:ALPHA "t"]]]]]]]
         
         (uri-parser "mailto:John.Doe@example.com")
         [:URI [:SCHEME [:ALPHA "m"] [:ALPHA "a"] [:ALPHA "i"] [:ALPHA "l"] [:ALPHA "t"] [:ALPHA "o"]] ":" [:HIER-PART [:PATH-EMPTY [:PCHAR [:UNRESERVED [:ALPHA "J"]]] [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "h"]]] [:PCHAR [:UNRESERVED [:ALPHA "n"]]] [:PCHAR [:UNRESERVED "."]] [:PCHAR [:UNRESERVED [:ALPHA "D"]]] [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "e"]]] [:PCHAR "@"] [:PCHAR [:UNRESERVED [:ALPHA "e"]]] [:PCHAR [:UNRESERVED [:ALPHA "x"]]] [:PCHAR [:UNRESERVED [:ALPHA "a"]]] [:PCHAR [:UNRESERVED [:ALPHA "m"]]] [:PCHAR [:UNRESERVED [:ALPHA "p"]]] [:PCHAR [:UNRESERVED [:ALPHA "l"]]] [:PCHAR [:UNRESERVED [:ALPHA "e"]]] [:PCHAR [:UNRESERVED "."]] [:PCHAR [:UNRESERVED [:ALPHA "c"]]] [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "m"]]]]]]
         
         (uri-parser "tel:+1-816-555-1212")
         [:URI [:SCHEME [:ALPHA "t"] [:ALPHA "e"] [:ALPHA "l"]] ":" [:HIER-PART [:PATH-EMPTY [:PCHAR [:SUB-DELIMS "+"]] [:PCHAR [:UNRESERVED [:DIGIT "1"]]] [:PCHAR [:UNRESERVED "-"]] [:PCHAR [:UNRESERVED [:DIGIT "8"]]] [:PCHAR [:UNRESERVED [:DIGIT "1"]]] [:PCHAR [:UNRESERVED [:DIGIT "6"]]] [:PCHAR [:UNRESERVED "-"]] [:PCHAR [:UNRESERVED [:DIGIT "5"]]] [:PCHAR [:UNRESERVED [:DIGIT "5"]]] [:PCHAR [:UNRESERVED [:DIGIT "5"]]] [:PCHAR [:UNRESERVED "-"]] [:PCHAR [:UNRESERVED [:DIGIT "1"]]] [:PCHAR [:UNRESERVED [:DIGIT "2"]]] [:PCHAR [:UNRESERVED [:DIGIT "1"]]] [:PCHAR [:UNRESERVED [:DIGIT "2"]]]]]]

         (uri-parser "telnet://192.0.2.16:80/")
         [:URI [:SCHEME [:ALPHA "t"] [:ALPHA "e"] [:ALPHA "l"] [:ALPHA "n"] [:ALPHA "e"] [:ALPHA "t"]] ":" [:HIER-PART "//" [:AUTHORITY [:HOST [:REG-NAME [:UNRESERVED [:DIGIT "1"]] [:UNRESERVED [:DIGIT "9"]] [:UNRESERVED [:DIGIT "2"]] [:UNRESERVED "."] [:UNRESERVED [:DIGIT "0"]] [:UNRESERVED "."] [:UNRESERVED [:DIGIT "2"]] [:UNRESERVED "."] [:UNRESERVED [:DIGIT "1"]] [:UNRESERVED [:DIGIT "6"]]]] ":" [:PORT [:DIGIT "8"] [:DIGIT "0"]]] [:PATH-ABEMPTY "/" [:SEGMENT]]]]
         
         (uri-parser "urn:oasis:names:specification:docbook:dtd:xml:4.1.2")
         [:URI [:SCHEME [:ALPHA "u"] [:ALPHA "r"] [:ALPHA "n"]] ":" [:HIER-PART [:PATH-EMPTY [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "a"]]] [:PCHAR [:UNRESERVED [:ALPHA "s"]]] [:PCHAR [:UNRESERVED [:ALPHA "i"]]] [:PCHAR [:UNRESERVED [:ALPHA "s"]]] [:PCHAR ":"] [:PCHAR [:UNRESERVED [:ALPHA "n"]]] [:PCHAR [:UNRESERVED [:ALPHA "a"]]] [:PCHAR [:UNRESERVED [:ALPHA "m"]]] [:PCHAR [:UNRESERVED [:ALPHA "e"]]] [:PCHAR [:UNRESERVED [:ALPHA "s"]]] [:PCHAR ":"] [:PCHAR [:UNRESERVED [:ALPHA "s"]]] [:PCHAR [:UNRESERVED [:ALPHA "p"]]] [:PCHAR [:UNRESERVED [:ALPHA "e"]]] [:PCHAR [:UNRESERVED [:ALPHA "c"]]] [:PCHAR [:UNRESERVED [:ALPHA "i"]]] [:PCHAR [:UNRESERVED [:ALPHA "f"]]] [:PCHAR [:UNRESERVED [:ALPHA "i"]]] [:PCHAR [:UNRESERVED [:ALPHA "c"]]] [:PCHAR [:UNRESERVED [:ALPHA "a"]]] [:PCHAR [:UNRESERVED [:ALPHA "t"]]] [:PCHAR [:UNRESERVED [:ALPHA "i"]]] [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "n"]]] [:PCHAR ":"] [:PCHAR [:UNRESERVED [:ALPHA "d"]]] [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "c"]]] [:PCHAR [:UNRESERVED [:ALPHA "b"]]] [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "k"]]] [:PCHAR ":"] [:PCHAR [:UNRESERVED [:ALPHA "d"]]] [:PCHAR [:UNRESERVED [:ALPHA "t"]]] [:PCHAR [:UNRESERVED [:ALPHA "d"]]] [:PCHAR ":"] [:PCHAR [:UNRESERVED [:ALPHA "x"]]] [:PCHAR [:UNRESERVED [:ALPHA "m"]]] [:PCHAR [:UNRESERVED [:ALPHA "l"]]] [:PCHAR ":"] [:PCHAR [:UNRESERVED [:DIGIT "4"]]] [:PCHAR [:UNRESERVED "."]] [:PCHAR [:UNRESERVED [:DIGIT "1"]]] [:PCHAR [:UNRESERVED "."]] [:PCHAR [:UNRESERVED [:DIGIT "2"]]]]]]
         
         (uri-parser "ldap://[2001:db8::7]/c=GB?objectClass?one")
         [:URI [:SCHEME [:ALPHA "l"] [:ALPHA "d"] [:ALPHA "a"] [:ALPHA "p"]] ":" [:HIER-PART "//" [:AUTHORITY [:HOST [:IP-LITERAL "[" [:IPV6ADDRESS [:H16 [:HEXDIG "2"] [:HEXDIG "0"] [:HEXDIG "0"] [:HEXDIG "1"]] ":" [:H16 [:HEXDIG "d"] [:HEXDIG "b"] [:HEXDIG "8"]] "::" [:H16 [:HEXDIG "7"]]] "]"]]] [:PATH-ABEMPTY "/" [:SEGMENT [:PCHAR [:UNRESERVED [:ALPHA "c"]]] [:PCHAR [:SUB-DELIMS "="]] [:PCHAR [:UNRESERVED [:ALPHA "G"]]] [:PCHAR [:UNRESERVED [:ALPHA "B"]]]]]] "?" [:QUERY [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "b"]]] [:PCHAR [:UNRESERVED [:ALPHA "j"]]] [:PCHAR [:UNRESERVED [:ALPHA "e"]]] [:PCHAR [:UNRESERVED [:ALPHA "c"]]] [:PCHAR [:UNRESERVED [:ALPHA "t"]]] [:PCHAR [:UNRESERVED [:ALPHA "C"]]] [:PCHAR [:UNRESERVED [:ALPHA "l"]]] [:PCHAR [:UNRESERVED [:ALPHA "a"]]] [:PCHAR [:UNRESERVED [:ALPHA "s"]]] [:PCHAR [:UNRESERVED [:ALPHA "s"]]] "?" [:PCHAR [:UNRESERVED [:ALPHA "o"]]] [:PCHAR [:UNRESERVED [:ALPHA "n"]]] [:PCHAR [:UNRESERVED [:ALPHA "e"]]]]])))

(deftest phone-uri
  (let [phone-uri-parser (binding [instaparse.abnf/*case-insensitive?* true]
                           (parser (slurp "test/instaparse/phone_uri.txt") :input-format :abnf))]
    (are [x y] (= x y)
         (phone-uri-parser "tel:+1-201-555-0123")
         [:TELEPHONE-URI
          "tel:"
          [:TELEPHONE-SUBSCRIBER
           [:GLOBAL-NUMBER
            [:GLOBAL-NUMBER-DIGITS
             "+"
             [:DIGIT "1"]
             [:PHONEDIGIT [:VISUAL-SEPARATOR "-"]]
             [:PHONEDIGIT [:DIGIT "2"]]
             [:PHONEDIGIT [:DIGIT "0"]]
             [:PHONEDIGIT [:DIGIT "1"]]
             [:PHONEDIGIT [:VISUAL-SEPARATOR "-"]]
             [:PHONEDIGIT [:DIGIT "5"]]
             [:PHONEDIGIT [:DIGIT "5"]]
             [:PHONEDIGIT [:DIGIT "5"]]
             [:PHONEDIGIT [:VISUAL-SEPARATOR "-"]]
             [:PHONEDIGIT [:DIGIT "0"]]
             [:PHONEDIGIT [:DIGIT "1"]]
             [:PHONEDIGIT [:DIGIT "2"]]
             [:PHONEDIGIT [:DIGIT "3"]]]]]])))

