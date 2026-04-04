
import React, { useState } from 'react'

const App = () => {

  const [JobDesc, setJobDesc] = useState("")
  const [Resume, setResume] = useState<any>(null);


 const  onSubmitHandler = async (e:any) => {
  e.preventDefault();
    const formData = new FormData()
    formData.append("jobDescription", JobDesc)
    formData.append("resume", Resume)
    try {

      const res = await fetch("http://127.0.0.1:8000/data/Jobdesc" , {
        method: "POST" , 
        body: formData
      })

      const data  =  await res.json()
      console.log(data);
      
      
    } catch (error) {
      console.log(error);
      
    }

 }




  return (
    <div className='h-screen w-screen '>
      <h1 className='text-3xl font-bold text-center mt-10'>Resume Analyzer and Job Matcher</h1>
      <p className='text-center mt-4 text-gray-600'>Upload your resume and find the best job matches!</p>
     
     
     <form onSubmit={onSubmitHandler}>
     <div>
      <h1>Enter you Job Descriptions</h1>
      <textarea value={JobDesc} onChange={(e) => setJobDesc(e.target.value)} className='w-full h-40 border border-gray-300 rounded p-2 mt-2' placeholder='Paste your job descriptions here...'></textarea>
     </div>
     
     
     
     
      <div className='flex justify-center mt-10'>
        <h1>Upload you Pdf</h1>
        <input type='file' accept='.pdf'
        onChange={(e)=>{  if (e.target.files) {
    setResume(e.target.files[0])
        }}}
        className='ml-4' />
      </div>


      <button>Submit</button>
      </form>

    </div>
  )
}

export default App