import spacy
import re

nlp = spacy.load("en_core_web_sm")

def KeywordExtractor(text):

    
    text = re.sub(r'[^a-zA-Z0-9\s\.\+#]', ' ', text)
    doc = nlp(text)
    keywords = set()
    for token in  doc:

        if token.is_stop or token.is_punct or token.is_space:
            continue
        word = token.lemma_.lower()
      
        if word.isdigit() or len(word) <= 2:
            continue


        if any(x in word for x in ["with", "using", "focused"]):
            continue

        keywords.add(word)

    return list(keywords)