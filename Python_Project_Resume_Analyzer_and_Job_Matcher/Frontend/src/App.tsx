import React, { useState, useRef } from 'react'
import { FaFileWaveform } from "react-icons/fa6";
import { useNavigate } from 'react-router';

const App = () => {
  const navigator = useNavigate() 
  const [JobDesc, setJobDesc] = useState("")
  const [Resume, setResume] = useState<any>(null)
  const [previewUrl, setPreviewUrl] = useState<string | null>(null)
  const fileInputRef = useRef<HTMLInputElement>(null)


  const onSubmitHandler = async (e: any) => {
    e.preventDefault()
    const formData = new FormData()
    formData.append("jobDescription", JobDesc)
    formData.append("resume", Resume)
    try {
      const res = await fetch("http://127.0.0.1:8000/data/Jobdesc", {
        method: "POST",
        body: formData,
      })
      console.log("NOthing");
      
      const data = await res.json()
      
      navigator("/result" , {state : data})


    } catch (error) {
      console.log(error)
    }
  }

  const handleFileChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files && e.target.files[0]) {
      const file = e.target.files[0]
      setResume(file)
      const url = URL.createObjectURL(file)
      setPreviewUrl(url)
    }
  }

  const handleRemoveFile = () => {
    setResume(null)
    setPreviewUrl(null)
    if (fileInputRef.current) fileInputRef.current.value = ""
  }

  return (
    <div className="min-h-screen  text-neutral-800 font-sans">
     
      


      <div className="max-w-4xl mx-auto px-6 py-14">
    
        <div className="mb-12">
          <h1 className="text-3xl font-semibold tracking-tight text-neutral-900">Resume Analyzer</h1>
          <p className="mt-2 text-neutral-500 text-sm leading-relaxed">
            Paste a job description and upload your resume to evaluate your match.
          </p>
        </div>

        <form onSubmit={onSubmitHandler} className="space-y-10">

          <div className="space-y-2">
            <label className="block text-xs font-semibold uppercase tracking-widest text-neutral-400">
              Job Description
            </label>
            <textarea
              value={JobDesc}
              onChange={(e) => setJobDesc(e.target.value)}
              placeholder="Paste the job description here..."
              className="w-full h-44 bg-white border border-neutral-200 rounded-xl px-4 py-3 text-sm text-neutral-800 placeholder-neutral-300 "
            />
          </div>

   


          <div className="space-y-2">
            <label className="block text-xs font-semibold uppercase tracking-widest text-neutral-400">
              Resume (PDF)
            </label>





            {!Resume ? (
              <div
                onClick={() => fileInputRef.current?.click()}
                className="w-full h-36 border-2 border-dashed border-neutral-200 rounded-xl flex flex-col items-center justify-center cursor-pointer hover:border-neutral-400 hover:bg-neutral-100 transition group"
              >
                <svg className="w-8 h-8 text-neutral-300 group-hover:text-neutral-500 transition mb-2" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={1.5} d="M4 16v1a3 3 0 003 3h10a3 3 0 003-3v-1m-4-8l-4-4m0 0L8 8m4-4v12" />
                </svg>
                <p className="text-sm text-neutral-400 group-hover:text-neutral-600 transition">Click to upload your PDF</p>



                <p className="text-xs text-neutral-300 mt-1">PDF only</p>
              </div>
            ) : (
              <div className="space-y-3">
           
                <div className="flex items-center justify-between bg-white border border-neutral-200 rounded-xl px-4 py-3">

                  <div className="flex items-center gap-3">
                    <div className="w-8 h-8 bg-red-50 rounded-lg flex items-center justify-center">
                    <FaFileWaveform />
                    </div>
                    <div>
                      <p className="text-sm font-medium text-neutral-800 truncate max-w-xs">{Resume.name}</p>
                      <p className="text-xs text-neutral-400">
                        {(Resume.size / 1024).toFixed(1)} KB    
                        </p>
                    </div>
                  </div>
                  <button

                    type="button"
                    onClick={handleRemoveFile}
                    className="text-neutral-400 hover:text-red-500 transition p-1 rounded-lg hover:bg-red-50"
                  >
                    <svg className="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M6 18L18 6M6 6l12 12" />
                    </svg>
                  </button>
                </div>

                
                {previewUrl  
                && (
                  <div className="rounded-xl overflow-hidden border border-neutral-200 bg-white shadow-sm">
                    <div className="flex items-center justify-between px-4 py-2 border-b border-neutral-100 bg-neutral-50">
                      <span className="text-xs text-neutral-400 font-medium">Preview</span>
                      <a
                        href={previewUrl}
                        target="_blank"
                        rel="noopener noreferrer"
                        className="text-xs text-neutral-500 hover:text-neutral-900 transition underline underline-offset-2"
                      >
                        Open full
                      </a>
                    </div>



                    <iframe
                      src={previewUrl}
                      className="w-full h-96"
                      title="PDF Preview"
                    />


                  </div>
                )}
              </div>
            )}

            <input
              ref={fileInputRef}
              type="file"
              accept=".pdf"
              onChange={handleFileChange}
              className="hidden"
            />
          </div>

          {/* Submit */}
          <div className="pt-2">
            <button type="submit" disabled={!JobDesc.trim() || !Resume}  className="w-full bg-neutral-900 text-white text-sm font-medium py-3 rounded-xl hover:bg-neutral-700 disabled:opacity-30 disabled:cursor-not-allowed transition" >
              Analyze Resume
            </button>
          </div>
        </form>
      </div>
    </div>
  )
}

export default App