def Comparer_keywords(resume_words , job_desc_words):
   
    matching_words = []
    missing_words = []
    for i in job_desc_words:
        if i in resume_words:
            matching_words.append(i)
        else:
            missing_words.append(i)
    
    analytics = {
        "Matching_words": matching_words , 
        "Missing_words" : missing_words, 
        "Score": round((len(matching_words) / len(job_desc_words)) * 100, 2)
    }
    return analytics






















