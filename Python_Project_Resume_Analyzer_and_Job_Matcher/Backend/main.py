import io
from fastapi import FastAPI, File, Form, UploadFile
from PdfExtractor import PdfExtractor


# python -m uvicorn main:app --reload
app = FastAPI()

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

    return {"nothing": "nothing to see here"}