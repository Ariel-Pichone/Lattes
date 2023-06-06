'use client';

import { Button, Label, Modal, Select, TextInput } from 'flowbite-react';
import { useState, useEffect } from 'react';
import { HiOutlineExclamationCircle } from '@react-icons/all-files/hi/HiOutlineExclamationCircle';
import { MdDelete } from '@react-icons/all-files/md/MdDelete';
import { BiSearchAlt2 } from '@react-icons/all-files/bi/BiSearchAlt2';
import { useForm } from 'react-hook-form';
import Instituto from '../instituto/page';
// import * as ReactSelect from 'react-select';

export default function Grafo() {

  return (
    <div className="mx-2">
      <div className="flex justify-between items-center">
        <h1 className="text-4xl font-bold px-4 py-6">Grafo</h1>

        <form
          className='flex justify-between items-center"'
        >

          <div className="mr-4" id="select">
            <Select>
              <option value="">Instituto</option>
 
            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select >
              <option value="">Pesquisador</option>

            </Select>
          </div>
          <div className="mr-4" id="select">
            <Select >
              <option value="">Tipo Prod.</option>
              <option>Artigo</option>
              <option>Livro</option>
            </Select>
          </div>

          <div className="mr-4" id="select">
            <Select >
              <option value="">Tipo Vértice</option>
              <option>Pesquisador</option>
              <option>Instituto</option>
            </Select>
          </div>


          <div className="flex justify-between items-center">
            <button
              type="submit"
              className="text-white bg-blue-700 hover:bg-blue-800 focus:ring-4 focus:ring-blue-300 font-medium rounded-lg text-sm px-5 py-2.5 mr-2 dark:bg-blue-600 dark:hover:bg-blue-700 focus:outline-none dark:focus:ring-blue-800"
            >
              Aplicar
            </button>
          </div>
        </form>
      </div>

      <div className="relative overflow-x-auto shadow-md sm:rounded-lg">
        <table className="w-full text-sm text-center text-gray-500 dark:text-gray-400">
          <thead className="text-xs text-gray-700 uppercase bg-gray-300 dark:bg-gray-700 dark:text-gray-400">
            <tr>
              <th scope="col" className="px-6 py-3 max-w-sm">
                Vértice (Cor)
              </th>
              <th scope="col" className="px-6 py-3">
                Valor NP (início)
              </th>
              <th scope="col" className="px-6 py-3">
                Valor NP (fim)
              </th>
            </tr>
          </thead>
          <tbody>
          <tr>
            <th className="px-6 py-3 max-w-sm">
              <Select >
               <option value="">Vermelha</option>
               <option>Roxa</option>
               <option>Azul</option>
               <option>Verde</option>
               <option>Amarela</option>
              </Select>
              <Select >
               <option value="">Roxa</option>
               <option>Vermelha</option>
               <option>Azul</option>
               <option>Verde</option>
               <option>Amarela</option>
              </Select>
              <Select >
               <option value="">Azul</option>
               <option>Vermelha</option>
               <option>Roxa</option>
               <option>Verde</option>
               <option>Amarela</option>
              </Select>
              <Select >
               <option value="">Verde</option>
               <option>Vermelha</option>              
               <option>Roxa</option>
               <option>Azul</option>
               <option>Amarela</option>
              </Select>
              <Select >
               <option value="">Amarela</option>
               <option>Vermelha</option>
               <option>Roxa</option>
               <option>Azul</option>
               <option>Verde</option>
              </Select>
            </th>
            <td className="px-6 py-4">
            <TextInput
              className="w-23"
              placeholder="1"
              disabled = "true"
            />
            <TextInput
              className="w-23"
              placeholder="6"
              disabled = "true"
            />
            <TextInput
              className="w-23"
              placeholder="9"
              disabled = "true"
            />
            <TextInput
              className="w-23"
              placeholder="11"
              disabled = "true"
            />
            <TextInput
              className="w-23"
              placeholder="16"
              disabled = "true"
            />                                              
            </td>
            <td className="px-6 py-4">
            <TextInput
              className="w-23"
              placeholder="5"
            />
            <TextInput
              className="w-23"
              placeholder="8"
            />
            <TextInput
              className="w-23"
              placeholder="10"
            />
            <TextInput
              className="w-23"
              placeholder="15"
            />
            <TextInput
              className="w-23"
              placeholder="20"
            />
            </td>
              </tr>
          </tbody>
        </table>
      </div>

    </div>
  );
}
