import io
from fastapi import FastAPI, File, Form, UploadFile
from PdfExtractor import PdfExtractor
from KeywordExtractor import KeywordExtractor
from Comparer import Comparer_keywords
from fastapi.middleware.cors import CORSMiddleware


# python -m uvicorn main:app --reload
app = FastAPI()


app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173"],  # your React dev server
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/nothing")
def root():
    return {"Message": "nothing to see here"}


@app.post("/data/Jobdesc")
async def upload_resume(jobDescription: str = Form(...), resume: UploadFile = File(...)):
    print(jobDescription)
    print(resume.filename)
    resume_content = await resume.read()
    pdf_file = io.BytesIO(resume_content)
    extracted_text = PdfExtractor(pdf_file)
    print(extracted_text)
    resume_keywords = KeywordExtractor(extracted_text)
    jobDescription_keywords = KeywordExtractor(jobDescription)
    Comparer = Comparer_keywords(resume_keywords, jobDescription_keywords)

    print(Comparer)
    return Comparer
