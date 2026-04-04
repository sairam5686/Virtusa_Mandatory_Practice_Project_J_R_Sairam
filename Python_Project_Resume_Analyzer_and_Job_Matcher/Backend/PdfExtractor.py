from pypdf import PdfReader

def PdfExtractor(pdfdata):
    reader = PdfReader(pdfdata)
    text = ""
    for page in reader.pages:
        text += page.extract_text()
    return text