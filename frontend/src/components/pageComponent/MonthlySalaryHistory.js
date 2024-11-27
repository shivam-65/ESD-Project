import React,{useEffect, useState} from "react";
import axios from 'axios';

const MonthlySalaryHistoryPage = ({eid, email, fname, lname, title,month, year}) => {
    const[history, setHistory] = useState([]);
    console.log(eid, month, year);


    const monthlySalaryHistory = async() => {

        await axios.get(`http://localhost:8080/salary/history/${eid}/${month}/${year}`)
        .then((response) => {
            console.log(`monthy salary of month of ${month}-${year}`,response.data);
            setHistory(response.data); //storing response into history state

        })
        .catch((error) => {
            console.log(error);
        })
    } 

    useEffect(() => {
        monthlySalaryHistory();
      },[month, year]);


    function toMonthName(m) {
        const date = new Date();
        date.setMonth(m - 1);
      
        return date.toLocaleString([], {
          month: 'long', 
        });
      }
      
      console.log(toMonthName(month));


    return (
        <div className="container-fluid py-5 border">
            {(history.length !== 0) ?
            <div className="row">
                <div className='col-md-12'>
                    <div className="text-center lh-1 mb-2">
                        <h6 className="fw-bold">Payment slip</h6> <span className="fw-normal">Payment slip for the month of {toMonthName(month)} {year}</span>
                    </div>
                    <div class="d-flex justify-content-end"> <span>Working Branch : Upcoming Universe</span> </div>
                    <div className="mt-3">
                        <div class="row">
                            <div class="d-flex justify-content-start">
                                <div> <span class="fw-bolder">Employee ID: </span>{eid}</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="d-flex justify-content-start">
                                <div> <span class="fw-bolder">Employee Name: </span>{title}. {fname} {lname}</div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="d-flex justify-content-start">
                                <div> <span class="fw-bolder">Employee Email: </span>{email}</div>
                            </div>
                        </div>
                    </div>
                        <div className="align-middle mt-5">
                        <table className="table">
                            <thead>
                                <tr>
                                <th scope="col">Reference Id</th>
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
                                                <td className="text-capitalize">{hist.description}</td>
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
            </div> : 
            <div className="card">
                <div className="card-header">
                    You were not paid on this date.
                </div>
            </div>
            }
        </div>
    )
}

export default MonthlySalaryHistoryPage;