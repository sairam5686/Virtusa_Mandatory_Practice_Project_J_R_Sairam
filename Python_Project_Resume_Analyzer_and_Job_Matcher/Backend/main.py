import io
from fastapi import FastAPI, File, Form, UploadFile
from PdfExtractor import PdfExtractor
from KeywordExtractor import KeywordExtractor
from Comparer import Comparer_keywords
from fastapi.middleware.cors import CORSMiddleware


app = FastAPI()


app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173"],  

    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.post("/data/Jobdesc")
async def upload_resume(jobDescription: str = Form(...), resume: UploadFile = File(...)):
    resume_content =await resume.read()
    pdf_fle =io.BytesIO(resume_content)
    extracted_text =PdfExtractor(pdf_fle)
    resume_kyeword =  KeywordExtractor(extracted_text)
    jobDescription_keywords =KeywordExtractor(jobDescription)
    Comparer =  Comparer_keywords(resume_kyeword, jobDescription_keywords)

    return Comparer
