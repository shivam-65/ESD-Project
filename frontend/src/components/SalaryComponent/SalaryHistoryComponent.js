import React, { useState, useEffect} from 'react';
import axios from 'axios';
import MonthlySalaryHistoryPage from '../pageComponent/MonthlySalaryHistory';
import swal from 'sweetalert'; 

const SalaryHistoryComponent= ({email, employeeId, firstname, lastname, title}) => {

    let m = new Date();
    const monthName = m.toLocaleString('default', { month: 'long' });
    let y = m.getFullYear(); 
    m = m.getMonth()+1; 

    const[history, setHistory] = useState([]);
    const[month, setMonth] = useState(m);
    const[year, setYear] = useState(y);

    useEffect(() => {
        salaryHistory();
      },[]);

    const salaryHistory = async () => {
        try {
          console.log("Fetching salary history for employeeId:", employeeId);

          const response = await axios.get(`http://localhost:8080/salary/history/${employeeId}`);
          console.log("Response data:", response.data);
      
          setHistory(response.data); // Storing the JSX elements in history variable
        } catch (error) {
          console.error('Error fetching salary history:', error);
          swal("Error fetching salary history, please check your internet connection!");
        }
      };
   
    function toMonthName(m) {
        const date = new Date();
        date.setMonth(m - 1);
        
        return date.toLocaleString([], {
            month: 'long', 
        });
        }
    
 
    const uniqueMonths = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
    const uniqueYears = [...new Set(history.map((item) => item.payment_date.substring(0,4)))]

    const Print = () =>{     
    console.log('print');  
    let printContents = document.getElementById('toPrint').innerHTML;
    let originalContents = document.body.innerHTML;
    document.body.innerHTML = printContents;
    window.print();
    document.body.innerHTML = originalContents; 
    window.location.reload(true);

}

    return (
            <div className='container-fluid px-5 py-5'>
                <div className='row row-cols-md-2'>
                    <div className="card col col-md-3 text-white bg-dark h-50">
                        <div className="card-header text-uppercase fw-bold">
                            Employee Details
                        </div>
                        <div className="card-body">
                            <p className="card-text text-start">Name: {firstname} {lastname}</p>
                            <p className="card-text text-start">Title: {title}</p>
                            <p className="card-text text-start">Employee ID: {employeeId}</p>
                            <p className="card-text text-start">Email: {email}</p>
                        </div>
                    </div>
           
                    <div className='card col col-md-8 offset-md-1'>
                        <div className="card-header text-uppercase fw-bold">
                            Salary History
                        </div>
                    
                        <div className="card-body overflow-auto" style={{height:"50vh"}}>  
                            <table className="table">
                                <thead className='thead-dark'>
                                    <tr>
                                    <th scope="col">Reference ID</th>
                                    <th scope="col">Description</th>
                                    <th scope="col">Payment Date</th>
                                    <th scope="col">Amount</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        history.map((hist, index) => {
                                            return(
                                                <tr key={index}>
                                                    <th scope="row">{hist.salaryId}</th>
                                                    <td className='text-capitalize'>{hist.description}</td>
                                                    <td>{hist.payment_date.substring(0, hist.payment_date.length)}</td>
                                                    <td>{hist.amount}</td>
                                                </tr>
                                            )
                                        })
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                    {/* </div> */}
                </div>
           
                <div className='row mt-5 w-50'>
                    <div className='col input-group mb-3'>
                        <div className="input-group-prepend">
                            <label className="input-group-text" htmlFor="monthSelect">Select month</label>
                        </div>
                        <select
      
                        onChange={(e)=>setMonth(parseInt(e.target.value))} 
                        className="form-select" id="monthSelect"
                        >
                            <option selected disabled value="">{monthName}</option> 
                            {
                                uniqueMonths.map((mth)=>{
                                    return(
                                        <option value={mth}>{toMonthName(mth)}</option>
                                    );
                                })
                            }
                        </select>
                    </div>
                    <div className='col input-group mb-3'>
                        <div className="input-group-prepend">
                            <label className="input-group-text" htmlFor="yearSelect">select year</label>
                        </div>
                        <select
                        onChange={(e)=>setYear(parseInt(e.target.value))}
                        className="form-select" id="yearSelect"
                        >
                            <option selected disabled value="">{year}</option> 
                            {
                                uniqueYears.map((year)=>{
                                    var yearVal = parseInt(year);
                                    return(
                                        <option value={yearVal}>{yearVal}</option>
                                    );
                                })
                            }
                        </select>
                    </div>
                </div>
   
                {
             
                    <div className='col mb-5 mt-5'>
                        <button className="btn btn-dark" onClick={Print}>
                        Download Salary Slip
                        </button>
                    </div>
                }
                <div className='row mt-4'>
                    {
                 
                    <>
                            <div className="col-12 col-md-8 offset-md-2" id="toPrint"> 
                                <MonthlySalaryHistoryPage
                                eid={employeeId}
                                email={email}
                                fname={firstname}
                                lname={lastname}
                                title={title}
                                month={month}
                                year={year}
                                />
                            </div>
               
                    </>
                    }       
                </div>
            </div>
    );
}


export default SalaryHistoryComponent;