import io
from fastapi import FastAPI, File, Form, UploadFile
from PdfExtractor import PdfExtractor
from KeywordExtractor import KeywordExtractor
from Comparer import Comparer_keywords
from fastapi.middleware.cors import CORSMiddleware
import google.generativeai as genai

app = FastAPI()


app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173" ,  "http://127.0.0.1:5173"],  

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
    
    
    genai.configure(api_key="AIzaSyDMDp3qrlr9ST1hGccNIp7TrGB2voaVG2I")
    model = genai.GenerativeModel("gemini-2.5-flash" , 
                                  system_instruction ="you are an professional resume analyser,your task is to give suggestions to improve resume based on job description and resume text provided" )
    content = f"Here is the job description {jobDescription} and here is the resume texts {extracted_text} "
    print("AI Content" , content)
    

    try:
        llm_response = model.generate_content(content)
        
        ai_text = llm_response.text if llm_response.text else "No suggestions generated"

    except Exception as e:   
        print("LLM ERROR:", e)
        ai_text = "AI service unavailable due to api request is exceeded. Basic keyword comparison is provided."


    Comparer =  Comparer_keywords(resume_kyeword, jobDescription_keywords , ai_text)
    return Comparer


