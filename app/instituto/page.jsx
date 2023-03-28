'use client';

import { Button, Modal } from 'flowbite-react';
import { useState, useEffect } from 'react';
import { HiOutlineExclamationCircle } from '@react-icons/all-files/hi/HiOutlineExclamationCircle';

export default function Instituto() {
  const [data, setData] = useState(null);
  const [isLoading, setLoading] = useState(true);
  const [showDeleteConfirmation, setShowDeleteConfirmation] = useState(false);
  const [objToDelete, setObjToDelete] = useState({});

  function handleDelete(instituto) {
    setObjToDelete(instituto), setShowDeleteConfirmation(true);
  }

  function deleteInstituto(id) {
    fetch(`http://localhost:8080/instituto/${id}`)
      .then(console.log('deleted'))
      .then(setShowDeleteConfirmation(!showDeleteConfirmation))
      .then(setLoading(true))
      .catch((err) => console.log(err));
  }

  useEffect(() => {
    setLoading(true);
    fetch('http://localhost:8080/instituto/')
      .then((res) => res.json())
      .then((data) => {
        setData(data);
        setLoading(false);
      })
      .catch((err) => console.log(err));
  }, [isLoading]);

  if (isLoading) return <p>Loading...</p>;
  if (!data) return <p>No data</p>;

  return (
    <div className="mx-2">
      <div className="flex justify-between items-center">
        <h1 className="text-4xl font-bold px-4 py-6">Instituto</h1>
        <button
          type="button"
          className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 mb-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
        >
          Incluir
        </button>
      </div>

      <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table className="w-full text-sm text-left text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-50 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" className="px-6 py-3">
                Nome Instituto
              </th>
              <th scope="col" className="px-6 py-3">
                Acrônimo
              </th>
              <th>Ações</th>
            </tr>
          </thead>
          <tbody>
            {data.map((Instituto) => (
              <tr className="bg-white border-b dark:bg-gray-900 dark:border-gray-700">
                <th
                  scope="row"
                  className="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white"
                >
                  {Instituto.nome}
                </th>
                <td className="px-6 py-4">{Instituto.acronimo}</td>
                <td className="px-6 py-4 space-x-3">
                  {/* <a
                    href="#"
                    className="font-medium text-blue-600 dark:text-blue-500 hover:underline"
                  >
                    Editar
                  </a> */}
                  <a
                    onClick={() => handleDelete(Instituto)}
                    className="font-medium text-red-600 dark:text-red-500 hover:underline cursor-pointer"
                  >
                    Excluir
                  </a>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {/* MODAL DELETE CONFIRMATION */}
      <>
        <Modal
          show={showDeleteConfirmation}
          size="md"
          popup={true}
          onClose={() => setShowDeleteConfirmation(!showDeleteConfirmation)}
          objToDelete={objToDelete}
        >
          <Modal.Header />
          <Modal.Body>
            <div className="text-center">
              <HiOutlineExclamationCircle className="mx-auto mb-4 h-14 w-14 text-gray-400 dark:text-gray-200" />
              <h3 className="mb-5 text-lg font-normal text-gray-500 dark:text-gray-400">
                Você tem certeza que deseja deletar o instituto{' '}
                {objToDelete.acronimo}?
              </h3>
              <div className="flex justify-center gap-4">
                <Button
                  color="failure"
                  onClick={() => deleteInstituto(objToDelete.id)}
                >
                  Sim, tenho ceteza
                </Button>
                <Button
                  color="gray"
                  onClick={() =>
                    setShowDeleteConfirmation(!showDeleteConfirmation)
                  }
                >
                  Não, cancelar
                </Button>
              </div>
            </div>
          </Modal.Body>
        </Modal>
      </>
    </div>
  );
}
