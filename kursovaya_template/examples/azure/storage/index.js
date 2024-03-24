// Copyright (c) Microsoft Corporation.
// Licensed under the MIT license.

/**
 * @summary list containers in an account, showing options for paging, resuming paging, etc.
 * @azsdk-weight 70
 */

import { BlobServiceClient, StorageSharedKeyCredential } from "@azure/storage-blob";

// Load the .env file if it exists
import * as dotenv from "dotenv";
dotenv.config();

async function main() {
    // Enter your storage account name and shared key
    const account = process.env.ACCOUNT_NAME || "";
    const accountKey = process.env.ACCOUNT_KEY || "";

    // Use StorageSharedKeyCredential with storage account and account key
    // StorageSharedKeyCredential is only available in Node.js runtime, not in browsers
    const sharedKeyCredential = new StorageSharedKeyCredential(account, accountKey);

    // List containers
    const blobServiceClient = new BlobServiceClient(
        `https://${account}.blob.core.windows.net`,
        sharedKeyCredential,
    );

    // Iterate over all containers in the account
    console.log("Containers:");
    for await (const container of blobServiceClient.listContainers()) {
        console.log(`- ${container.name}`);
    }

    // The iterator also supports iteration by page with a configurable (and optional) `maxPageSize` setting.
    console.log("Containers (by page):");
    for await (const response of blobServiceClient.listContainers().byPage({
        maxPageSize: 20,
    })) {
        console.log("- Page:");
        if (response.containerItems) {
            for (const container of response.containerItems) {
                console.log(`  - ${container.name}`);
            }
        }
    }

    const containerName = "avatars";
    const fileName = "./blonda2.jpg";


    const containerClient = blobServiceClient.getContainerClient(containerName);
    const blockBlobClient = containerClient.getBlockBlobClient(fileName);


    await blockBlobClient.uploadFile("./blonda2.jpg");

    await blockBlobClient.setHTTPHeaders({
        blobContentType: "image/jpeg"
    });
}

main().catch((error) => {
    console.error(error);
    process.exit(1);
});