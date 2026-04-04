
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.tsx'
import { createBrowserRouter, RouterProvider } from 'react-router'
import AnalyticsScore from './AnalyticsScore.tsx'

const routes =  createBrowserRouter([{
  path: '/',
  element: <App />
} , {
  path: "/result" , 
  element: <AnalyticsScore />
}
])


createRoot(document.getElementById('root')!).render(
  <>
  <RouterProvider router={routes} />
  </>
)
