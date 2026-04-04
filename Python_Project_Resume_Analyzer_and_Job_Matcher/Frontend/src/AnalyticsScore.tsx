import React from 'react'
import { useLocation } from 'react-router'

const AnalyticsScore = () => {

  

  const { state: result } = useLocation();
  console.log(result);
  return (
   
    <div className='h-screen w-full flex flex-col items-center pt-10 pr-10 pl-10'>

        <div>      
        <h1 className='text-3xl font-semibold'> Result Analytics From your resume </h1>
        </div>

        <div className='h-[500px] w-full flex flex-row gap-10  mt-20 '>


          <div className='h-[500px] w-[49%] bg-gray-200  rounded-2xl'>
            <div className='flex flex-col  '>
              <h1 className='text-2xl font-semibold text-center mt-10 mb-5'>Matched Words</h1>
              <div className='flex flex-row gap-2 flex-wrap'>
              {result?.Matching_words?.map((word: string, index: number) => {
              return ( <p key={index} className="text-md bg-emerald-500 rounded-xl p-2 ">{word} </p>  );
            })}
            </div>
            </div>

            <div className='flex flex-col '>
              <h1 className='text-2xl font-semibold text-center mt-10 mb-3'>Missing Words</h1>
              <div className='flex flex-row gap-2 flex-wrap'>
              {result?.Missing_words?.map((word: string, index: number) => {
              return (
                <p key={index} className="text-md bg-red-500 rounded-xl p-2 ">{word} </p>);})}
              </div>

            </div>
          </div>




          <div className='h-[500px] w-[49%] bg-amber-200  rounded-2xl'>
            <h1 className='text-4xl font-semibold text-center mt-10 '>Score</h1>
            <div className='flex mt-10 justify-center h-full'>
              <p className='text-4xl font-semibold'>{result?.Score}% out of 100%</p>
         
            </div>
          </div>
        </div>

    </div>
  )
}

export default AnalyticsScore