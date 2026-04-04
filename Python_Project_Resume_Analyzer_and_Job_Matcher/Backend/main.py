from fastapi import FastAPI


# python -m uvicorn main:app --reload
app = FastAPI()

@app.get("/nothing")
def root():
    return {"Message": "nothing to see here"}