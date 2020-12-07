import React, { useEffect } from 'react';
import { Table } from 'react-bootstrap';
import api from '../../services/api';

const Clients: React.FC = () => {

  useEffect(() => {
    loadClients()
  }, []);

  async function loadClients() {
    const response = await  api.get('/');
    console.log(response);
  }

  return (
    <div className="container"> 
      <h1> Clients </h1> 
      <br/>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Username</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>1</td>
            <td>Mark</td>
            <td>Otto</td>
            <td>@mdo</td>
          </tr>
          <tr>
            <td>2</td>
            <td>Jacob</td>
            <td>Thornton</td>
            <td>@fat</td>
          </tr>
        </tbody>
      </Table>
    </div>
  );
}

export default Clients;