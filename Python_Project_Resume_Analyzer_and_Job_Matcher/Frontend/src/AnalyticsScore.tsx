import React, { useEffect, useState } from 'react'
import { useLocation } from 'react-router'

const AnalyticsScore = () => {

  
  const [Ratings, setRatings] = useState("")
  const { state: result } = useLocation();
  console.log(result);
useEffect(() => {
  
  if(result.Score >= 90){
    setRatings("Look Alright")
  }
  else if(result.Score > 60){
    setRatings("Look Alright")
  }
  else{
    setRatings("Need Improvement")

  }

}, [])

            
  return (
    <div className='max-w-4xl mx-auto px-6 py-14'>
      <div>
        <p className='font-mono font-light text-gray-400'>
          Resume Analysis
        </p>
        <h1 className='text-5xl font-semibold'>
          Your Matching  <span className='text-emerald-400'> Score </span>
        </h1>
      </div>

      <div className='border-1 border-gray-200 mt-4'></div>

      <div className='flex flex-row gap-10'>

      {/* for matchers indicators */}
      <div className='flex flex-col  boder-1  mt-5  w-[75%] border-black'>
        
        
        
        <div className='flex flex-col bg-gray-200 rounded-xl px-5 py-4'> 
            <div className='flex flex-row mb-4 justify-between items-center'>
              <p className='font-bold text-gray-600 font-mono'>Matching Words</p>
              <p className='font-mono font-bold   px-2 py-1 bg-emerald-900 text-emerald-200 rounded-3xl text-center'>found {result?.Matching_words?.length}</p>
            </div>
            <div className='border-1 border-gray-300 mt-4 mb-3'></div>
              
              <div className=' flex flex-row flex-wrap gap-2 text-balance'>
              {result?.Matching_words?.map((word: string, index: number) => {
              return (
                 <p key={index}  className="font-mono font-bold   px-2 py-1 bg-emerald-900 text-emerald-200 rounded-3xl text-center">{word}</p>);})}
            </div>
        </div>
     
     
     

          
        <div className='flex flex-col bg-gray-200 rounded-xl mt-5 px-5 py-4'> 
            <div className='flex flex-row mb-4 justify-between items-center'>
              <p className='font-bold text-gray-600 font-mono'>Missing Words</p>
              <p className='font-mono font-bold   px-2 py-1 bg-red-900 text-red-200 rounded-3xl text-center'>found {result?.Missing_words?.length}</p>
            </div>
            <div className='border-1 border-gray-300 mt-4 mb-3'></div>
              
              <div className=' flex flex-row flex-wrap gap-2 text-balance'>
              {result?.Missing_words?.map((word: string, index: number) => {
              return (
                 <p key={index}  className="font-mono font-bold   px-2 py-1 bg-red-900 text-red-200 rounded-3xl text-center">{word}</p>);})}
            </div>
        </div>

     
      </div>





      
        
      <div className='bg-gray-200 items-center w-[40%] flex flex-col mt-5 rounded-2xl'>
              
                <p className='font-mono mt-20 font-bold text-gray-600'>
                  Your Matching Score
                </p>


                  <div className="relative size-40 mt-10">
                      <svg className="rotate-[135deg] size-full" viewBox="0 0 36 36" xmlns="http://www.w3.org/2000/svg">
                      
                        <circle cx="18" cy="18" r="16" fill="none" className="stroke-current text-foreground/10" stroke-width="1.5" stroke-dasharray={`${(result?.Score ?? 0) / 100 * 75} 100`} stroke-linecap="round"></circle>

                      
                        <circle cx="18" cy="18" r="16" fill="none" className="stroke-current text-blue-400" stroke-width="1.5" stroke-dasharray="37.5 100" stroke-linecap="round"></circle>
                      </svg>


                      <div className="absolute top-1/2 start-1/2 transform -translate-x-1/2 -translate-y-1/2 text-center">
                        <span className="text-4xl font-bold text-blue-400">{result?.Score}</span>
                        <span className="text-primary block">Score</span>
                      </div>
                    </div>


                    <h3 className='text-2xl text-blue-400 font-mono font-semibold'>
                    {Ratings}
                    </h3>

      </div>



      </div>

    </div>
  )
}

export default AnalyticsScore